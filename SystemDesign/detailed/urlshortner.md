
# URL Shortener System Design - Complete Interview Notes

## 🎯 Problem Statement
Design a URL shortening service like Bit.ly that converts long URLs into shorter, manageable links with redirection capabilities.

---

## 📊 Interview Framework (Follow This Order!)
1. **Requirements** (Functional + Non-functional)
2. **Core Entities** (High-level data model)
3. **API Design** (Contract between client/server)
4. **High-Level Design** (Basic working system)
5. **Deep Dives** (Scale, performance, reliability)

---

## 📋 Requirements

### Functional Requirements (In Scope)
- ✅ **URL Shortening**: Convert long URL → short URL
- ✅ **Redirection**: Access original URL via short URL  
- ✅ **Custom Aliases**: Optional user-defined short codes
- ✅ **Expiration**: Optional expiration dates for URLs

### Below the Line (Out of Scope)
- ❌ User authentication & account management
- ❌ Analytics & click tracking (though system should support future addition)

### Non-Functional Requirements
- **Uniqueness**: Ensure short codes never collide
- **Low Latency**: Redirection < 200ms (human perception of real-time)
- **High Availability**: 99.99% uptime (favor availability over consistency)
- **Scale**: 100M DAU, 1B total shortened URLs
- **Read-Heavy**: Massive read-to-write ratio (1000:1 typical)

### CAP Theorem Decision
- **Chosen**: Availability + Partition Tolerance
- **Reasoning**: Temporary inconsistency acceptable (users can retry failed redirects)
- **Not Banking/Ticketing**: No catastrophic failure if briefly unavailable

---

## 🏗️ Core Entities
1. **URL**: Maps short_code ↔ long_url (combined entity)
2. **User**: Creator of the shortened URL (minimal for now)

### Data Model
```sql
urls {
  short_code: VARCHAR(8) PRIMARY KEY
  long_url: VARCHAR(2048) 
  created_at: TIMESTAMP
  expires_at: TIMESTAMP
  user_id: INTEGER
  custom_alias: VARCHAR(100)
}

users {
  user_id: INTEGER PRIMARY KEY
  // ... additional metadata
}
```

---

## 🔌 API Design

### Create Short URL
```http
POST /urls
{
  "long_url": "https://www.example.com/very/long/url",
  "custom_alias": "optional_alias",      // ?optional
  "expiration_time": "optional_date"     // ?optional
}

Response:
{
  "short_url": "http://short.ly/abc123"
}
```

### Redirect to Original URL
```http
GET /{short_code}
→ HTTP 302 Redirect to original URL
```

**Critical Decision: 302 vs 301 Redirect**
- **302 (Temporary)**: ✅ RECOMMENDED
  - Never cached by browsers/DNS
  - Always hits our server (analytics capability)
  - Can update/delete URLs later
  - Visibility into system health
- **301 (Permanent)**: 
  - Cached by browsers (reduces server load)
  - Lose analytics and control
  - Can't track if system is working

---

## 🏛️ High-Level Design

### Initial Simple Architecture
```
[Client] → [Primary Server] → [Database]
           ↓
     Generate Short Code
     Store Mapping
     Return Short URL
```

### Components
- **Client**: Web/mobile application
- **Primary Server**: Business logic, validation, code generation
- **Database**: URL mappings storage (indexed on short_code)

### Flow
1. **URL Creation**: Client → Server → Validate → Generate Short Code → Store → Return
2. **URL Access**: Client → Server → DB Lookup → 302 Redirect

---

## 🔍 Deep Dives

### 1. Short URL Generation Strategies 🔑

#### Strategy Comparison Matrix
| Method | Uniqueness | Performance | Scalability | Security | Complexity |
|--------|------------|-------------|-------------|----------|------------|
| Counter + Base62 | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐⭐ |
| Random Generation | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |
| Hash-Based | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐⭐ |
| URL Prefix | ⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐ | ⭐⭐⭐⭐⭐ |

#### Option A: Counter-Based + Base62 Encoding (⭐ RECOMMENDED)
```
Process:
1. Global counter: 1, 2, 3, 4, 5...
2. Convert to Base62: [a-z, A-Z, 0-9] = 62 characters
3. Examples: 1→b, 62→10, 3844→bb, 238328→zzz

Capacity Analysis:
- 62^6 = ~57 billion combinations (6 characters)
- 62^7 = ~3.5 trillion combinations (7 characters)
```
**✅ Advantages:**
- **Guaranteed uniqueness** (no collisions ever)
- **Predictable length** (starts small, grows incrementally)
- **Fast generation** (O(1) counter + O(log n) encoding)
- **No database lookup** needed during generation
- **Optimal space utilization**

**❌ Disadvantages:**
- **Predictable pattern** (security/privacy concern)
- **Requires centralized counter** (scaling challenge)
- **Reveals business metrics** (total URLs created)

**Mitigating Predictability:**
- Start counter at random high number (e.g., 1,000,000)
- Use bijective functions (e.g., sqids library) for obfuscation
- Add random salt to encoding process
- Rate limiting to prevent scraping

---

#### Option B: Random Generation
```
Process:
1. Generate random string of fixed length (6-8 chars)
2. Use charset: [a-z, A-Z, 0-9] 
3. Check database for collision
4. Retry if collision found
```
**Birthday Paradox Analysis:**
- 6 chars: 62^6 = 57B combinations
- At 1B URLs: ~1.7% collision chance
- At 8B URLs: ~880K estimated collisions
- Performance degrades as database fills

**✅ Advantages:**
- **Unpredictable** (better security)
- **No centralized dependency**
- **Parallel generation** possible
- **Business metrics hidden**

**❌ Disadvantages:**
- **Collision probability** increases with scale
- **Database lookup required** for collision checking
- **Non-deterministic generation time**
- **Retry logic complexity**

---

#### Option C: Hash-Based (MD5/SHA256)
```
Process:
1. Hash the long URL: MD5(long_url)
2. Take first 6-8 characters of hash
3. Convert to Base62 if needed
4. Check for collision, handle if found
```
**✅ Advantages:**
- **Deterministic** (same URL = same short code)
- **Automatic deduplication**
- **No external dependencies**

**❌ Disadvantages:**
- **High collision risk** (birthday paradox applies)
- **Fixed computational overhead**
- **Still needs collision checking**
- **Potential hash prediction attacks**

---

#### Option D: URL Prefix (❌ DON'T USE)
```
Process:
1. Take first 5-7 characters of long URL
2. Use as short code
```
**Fatal Flaw:** Many URLs share prefixes (twitter.com, facebook.com)
**Result:** One-to-many mapping → system breaks

---

### 2. Database Optimization & Indexing

#### Primary Key Strategy
```sql
-- Make short_code the primary key
CREATE TABLE urls (
  short_code VARCHAR(8) PRIMARY KEY,  -- Automatic index
  long_url VARCHAR(2048) NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  expires_at TIMESTAMP,
  user_id INTEGER
);

-- Additional index for reverse lookup
CREATE INDEX idx_long_url ON urls(long_url);
```

#### Query Performance
- **Primary Key Lookup**: O(log n) with B-tree index
- **Modern DBs**: Highly optimized for equality lookups
- **Memory vs Disk**: Index typically kept in memory
- **Optional**: Hash index for O(1) lookup (usually overkill)

### 3. Caching Strategy for Low Latency

#### Redis Cache Implementation
```
Cache Type: Read-through, LRU eviction
Key: short_code
Value: long_url
TTL: Based on expiration_date or default (e.g., 24h)

Flow:
1. Check Redis cache
2. If miss: Query database → Update cache → Return
3. If hit: Return immediately (sub-ms response)
```

#### Cache Considerations
- **Hit Ratio**: Aim for >95% for hot URLs
- **Memory**: Size based on working set
- **Eviction**: LRU works well (old URLs rarely accessed)
- **Failure**: Cache miss falls back to database (degraded but functional)

#### CDN Option (Trade-off Discussion)
**Pros**: Geographic distribution, edge caching
**Cons**: Lose analytics visibility, same as 301 redirect issues

### 4. Scaling to 100M DAU & 1B URLs

#### Traffic Calculations (Key Interview Math)
```
Daily Active Users: 100M = 10^8
Assume: 1 redirect per user per day
Seconds per day: ~100K = 10^5

Average RPS: 10^8 / 10^5 = 10^3 = 1,000 RPS
Peak traffic (10x): 10,000 RPS
Burst traffic (100x): 100,000 RPS

Write traffic: Much lower (~1 RPS for new URL creation)
```

#### Server Scaling Strategy
```
Single server capacity: ~1,000 concurrent requests
Need for 10K-100K RPS: Horizontal scaling required

Options:
- Vertical Scaling: Bigger instance (expensive, limited)
- Horizontal Scaling: Multiple instances (preferred)
```

#### Microservices Architecture
```
Split by function due to read/write imbalance:

[API Gateway] 
    ├── [Read Service] × N instances (handles redirects)
    └── [Write Service] × M instances (creates URLs)
        
Where N >> M (maybe 100:1 ratio)
```

#### Auto-scaling Configuration
```
Read Service: Scale on 75% CPU/memory usage
Write Service: Minimal scaling (low traffic)
Load Balancer: Round-robin or least-connections
Cloud: AWS Auto Scaling Groups, GCP Managed Instance Groups
```

### 5. Database Scaling Analysis

#### Storage Calculation
```
Per URL record:
- short_code: 8 bytes
- long_url: 100 bytes (average)
- created_at: 8 bytes
- expires_at: 8 bytes  
- user_id: 8 bytes
- metadata: ~68 bytes
Total: ~200 bytes → round to 500 bytes for safety

1B URLs × 500 bytes = 500GB
```

**Conclusion**: Single database instance sufficient (modern SSDs handle TBs)

#### Database Technology Choice
- **PostgreSQL**: Recommended (mature, reliable)
- **MySQL**: Also viable
- **DynamoDB**: Cloud-native option
- **Reasoning**: With caching layer, any will work fine

#### Sharding (If Needed)
```sql
-- Shard by short_code if scaling required
Shard = hash(short_code) % num_shards
```

### 6. Global Counter Scaling Challenge

#### The Problem
```
Multiple Write Service instances → Multiple counters → Collisions!

Instance A: counter = 1, 2, 3...
Instance B: counter = 1, 2, 3...  ← Same values! ❌
```

#### Solution: Centralized Counter Service
```
[Write Service] → [Redis Counter] 
                 ↑
Redis INCR command: Atomic, single-threaded, fast
```

#### Counter Batching Optimization
```
Write Service requests batch of 1,000 counter values
Reduces Redis calls from 1-per-URL to 1-per-1000-URLs
Lost counters on server restart: No problem (3.5T capacity)

Implementation:
- Request: "Give me next 1,000 values"
- Redis: Returns current_counter, increments by 1,000
- Service: Uses values locally until exhausted
```

### 7. High Availability Implementation

#### Component Availability
- **Read Services**: Auto-scaling provides redundancy
- **Write Services**: Multiple instances
- **Database**: Replica + periodic snapshots to S3
- **Redis Cache**: Read-through fallback to DB (acceptable degradation)
- **Global Counter**: Redis HA mode + periodic snapshotting

#### Disaster Recovery
```
Database backup: Hourly snapshots to S3
Counter backup: Periodic counter value saves
Recovery time: < 1 hour with automation
```

---

## 🎨 Final Scaled Architecture

```
                    [Load Balancer]
                          |
                   [API Gateway]
                     /         \
              [Read Service]  [Write Service]
               (100 instances)  (2 instances)
                     |              |
              [Redis Cache] ← [Global Counter Redis]
                     |              |
                 [Database] ←-------/
                     |
              [Backup Storage S3]
```

### Key Design Decisions Summary
1. **Counter-based generation**: Guaranteed uniqueness
2. **302 redirect**: Maintains control and analytics capability  
3. **Microservices split**: Independent scaling for read/write
4. **Redis caching**: Sub-200ms response time
5. **Horizontal scaling**: Handle 100K RPS peaks
6. **Single database**: 500GB easily handled by modern systems

---

## 💡 Interview Tips & Follow-ups

### What Interviewers Look For
- **Systematic approach** (follow the framework)
- **Trade-off discussions** (302 vs 301, counter vs random)
- **Scaling calculations** (show the math)
- **Practical solutions** (don't over-engineer)

### Common Follow-up Questions
- **"How do you handle hot URLs?"** → Caching strategy, CDN discussion
- **"What if Redis goes down?"** → Read-through fallback, HA modes
- **"How do you prevent abuse?"** → Rate limiting, validation
- **"Custom domains support?"** → DNS configuration, certificate management
- **"How do you monitor the system?"** → Metrics, logging, alerting

### Red Flags to Avoid
- ❌ Starting with complex distributed systems
- ❌ Skipping requirements gathering
- ❌ Not considering the read/write imbalance
- ❌ Over-engineering early (keep it simple first)
- ❌ Ignoring the uniqueness constraint

### Pro Tips
- ✅ Show calculations (traffic, storage)
- ✅ Discuss trade-offs explicitly  
- ✅ Start simple, add complexity incrementally
- ✅ Communicate your framework upfront
- ✅ Ask clarifying questions about requirements

### Time Management (45-minute interview)
- **Requirements**: 5-8 minutes
- **Entities & API**: 3-5 minutes  
- **High-level design**: 8-10 minutes
- **Deep dives**: 20-25 minutes
- **Wrap-up**: 5 minutes
