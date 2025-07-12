# Web Crawler Design - Comprehensive Interview Notes

## Problem Scale & Requirements
- **Scale**: 10B pages, 2MB avg size, complete in 5 days
- **Storage**: 500TB/month, 30PB over 5 years  
- **QPS**: 400 pages/sec (800 peak)
- **Content**: HTML only, handle duplicates (30% of web)

## Core Requirements
- **Scalability**: Parallel processing across servers
- **Politeness**: Respect robots.txt, rate limiting (1 req/sec per domain)
- **Robustness**: Handle failures, bad HTML, malicious links
- **Extensibility**: Easy to add new content types
- **Fault Tolerance**: Resume without losing progress

## System Interface
- **Input**: Seed URLs
- **Output**: Extracted text data for LLM training
- **Data Flow**: URL → DNS → Fetch → Parse → Extract → Store → Repeat

## High-Level Architecture (Multi-Stage Pipeline)

```
Seed URLs → URL Frontier → URL Fetcher → HTML Storage (S3)
    ↑                          ↓
URL Storage ← URL Filter ← Text/URL Extractor ← Metadata DB
                          ↓
                    Text Storage (S3)
```

## Core Components Deep Dive

### 1. URL Frontier (Critical Component)
**Two-tier design for politeness + priority:**

**Front Queues (Priority Management):**
- Prioritizer assigns URLs to queues by PageRank/traffic/frequency
- High-priority queues selected more frequently
- Queue selector with bias toward higher priority

**Back Queues (Politeness Management):**
- One queue per host (wikipedia.com → queue b1)
- Queue router maps hostname to specific queue
- Worker threads process one host at a time with delays

**Storage Strategy:**
- Hybrid: Memory buffers + disk persistence
- Most URLs on disk, popular ones in memory
- Periodic buffer writes to disk

### 2. Multi-Stage Pipeline (Fault Tolerance)
**Stage 1: URL Fetcher**
- Fetches HTML, stores in blob storage
- Handles most failures (network, timeouts, server errors)
- Uses SQS with retry + exponential backoff + DLQ

**Stage 2: Text & URL Extractor**  
- Extracts text data from stored HTML
- Extracts new URLs, converts relative to absolute
- Updates metadata DB with processing status

**Benefits:**
- Isolate failures to single stage
- Independent scaling per stage  
- Retry without losing progress
- Easy to modify extraction logic

### 3. Politeness & Robots.txt
**Robots.txt Compliance:**
```
User-agent: *
Disallow: /private/
Crawl-delay: 10
```

**Implementation:**
- Download & cache robots.txt per domain
- Check disallow rules before crawling
- Respect crawl-delay directives
- Store last crawl time per domain in Metadata DB

**Rate Limiting (1 req/sec per domain):**
- Redis-based sliding window counter per domain
- Global rate limiting across all crawlers
- Add jitter to prevent synchronized behavior
- Put URLs back in queue if rate limit hit

### 4. Content Processing
**Duplicate Detection (30% of web is duplicates):**
- **URL-level**: Check Metadata DB before queuing
- **Content-level**: Hash comparison for same content, different URLs
- Store content hashes in DB index (faster than bloom filter)

**Spider Trap Prevention:**
- Set maximum crawl depth per URL
- Track depth in Metadata DB
- Stop crawling if depth threshold exceeded

## Scaling Strategy

### Crawler Scaling (10B pages in 5 days)
**Bandwidth Calculation:**
- Network-optimized instance: 400 Gbps
- Theoretical: 25,000 pages/sec per instance  
- Practical (30% utilization): 7,500 pages/sec
- **Need ~4 high-powered machines** for 5-day target

### Parser Worker Scaling
- Serverless auto-scaling (Lambda/ECS)
- Scale based on queue depth
- Lighter workload than fetching

### DNS Optimization (Critical Bottleneck)
- **DNS Caching**: Cache lookups per crawler
- **Multiple Providers**: Round-robin across providers
- **Rate Limit Management**: Pay for higher limits

## Technology Choices

### Queue: Amazon SQS
- Built-in retry + exponential backoff
- Visibility timeout for failure handling
- Dead letter queue for poison messages
- Easy configuration vs Kafka complexity

### Storage:
- **HTML/Text**: S3 (cheap, scalable blob storage)
- **Metadata**: DynamoDB (fast, scalable NoSQL)
- **Rate Limiting**: Redis (fast in-memory counters)

### Rate Limiting: Redis + Sliding Window
- Track requests per domain per second
- Distributed across all crawlers
- Add jitter to prevent thundering herd

## Robustness Techniques
- **Consistent Hashing**: Distribute load, easy scaling
- **State Persistence**: Save crawl progress for recovery
- **Exception Handling**: Graceful error handling
- **Data Validation**: Prevent system corruption
- **Circuit Breakers**: Stop hitting failing services
- **Timeouts**: Avoid hanging on slow servers

## Performance Optimizations
1. **Distributed Crawling**: Partition URL space across servers
2. **DNS Caching**: Avoid repeated DNS lookups (10-200ms each)
3. **Geographic Distribution**: Deploy near target sites
4. **Connection Pooling**: Reuse HTTP connections
5. **Content Filtering**: Skip large files, ads, spam

## Additional Considerations
- **Dynamic Content**: Use headless browsers (Puppeteer) for JS rendering
- **Monitoring**: Track crawler health, success rates, bottlenecks
- **Large Files**: Check Content-Length header, skip if too big
- **Continual Updates**: Add URL Scheduler for re-crawling based on frequency/importance

## Interview Expectations by Level

### Mid-Level (E4)
- **Focus**: 80% breadth, 20% depth
- Understand basic data flow and simple system design
- Discuss politeness and robots.txt at high level
- Basic scaling understanding
- Interviewer may drive later stages

### Senior (E5+)
- **Focus**: 60% breadth, 40% depth  
- Deep knowledge of queuing, caching, rate limiting
- Articulate architectural trade-offs clearly
- Proactively identify bottlenecks and solutions
- Drive entire interview with minimal guidance
- Advanced topics: fault tolerance, monitoring, optimization

## Key Interview Tips
- Start simple, add complexity incrementally
- Emphasize **URL Frontier** design (most critical)
- Always discuss **politeness** and **scale challenges**
- Show understanding of **real-world constraints**
- **Pipeline approach** for fault tolerance
- **Trade-off discussions** for technology choices
