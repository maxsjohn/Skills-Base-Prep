# URL Shortener - Additional Key Insights

## 📊 Traffic & Storage Calculations
```
Write Operations: 100M URLs/day
├── Per second: 100M ÷ (24 × 3600) = 1,160 RPS
└── Peak assumption: ~2,000-3,000 RPS

Read Operations: 10:1 ratio
├── Read RPS: 1,160 × 10 = 11,600 RPS  
└── Peak: ~20,000-30,000 RPS

Storage (10 years):
├── Total URLs: 100M × 365 × 10 = 365B URLs
├── Avg URL length: 100 bytes
└── Total storage: 365B × 100 = 36.5 TB
```

## 🔢 Hash Length Calculation
**Target**: Support 365 billion URLs  
**Character set**: [0-9, a-z, A-Z] = 62 characters

| Length | Capacity | Sufficient? |
|--------|----------|-------------|
| 6 chars | 62^6 = 56.8B | ❌ Too small |
| 7 chars | 62^7 = 3.5T | ✅ Perfect |
| 8 chars | 62^8 = 218T | ⚠️ Overkill |

**Conclusion**: 7 characters optimal

## 🏗️ Database Schema
```sql
url_mappings {
  id: BIGINT PRIMARY KEY          -- Unique ID for base62
  short_url: VARCHAR(7) UNIQUE    -- Generated short code  
  long_url: VARCHAR(2048)         -- Original URL
  created_at: TIMESTAMP          -- Creation time
  expires_at: TIMESTAMP          -- Optional expiration
}

Indexes:
- PRIMARY KEY on id (auto-generated)
- UNIQUE INDEX on short_url (lookup performance)
- INDEX on long_url (deduplication checks)
```

## ⚖️ Hash Approaches Comparison

### Hash + Collision Resolution
```
Process: Hash(longURL) → Take first 7 chars → Check collision → Retry if needed

Example:
- MD5("https://example.com") = "5a62509a84df9ee..."
- Take first 7: "5a62509"
- If exists in DB: append predefined string + rehash
```

**Pros**: Fixed length, no dependencies  
**Cons**: DB lookups for collision detection, performance overhead

**Optimization**: Bloom filters for collision pre-checking
- Space-efficient probabilistic data structure
- Reduces DB queries for collision detection
- False positives possible, false negatives impossible

### Base62 Conversion (Recommended)
```
Process: Generate unique ID → Convert to base62 → Use as short code

Example:
- Unique ID: 2009215674938
- Base62: 2×62² + 55×62¹ + 59×62⁰ = "2TX"
- Short URL: tinyurl.com/2TX
```

**Pros**: No collisions, predictable, efficient  
**Cons**: Requires unique ID generator, potential security issue

## 🔄 URL Shortening Flow (Base62)
```
1. Input: longURL
2. Check if longURL exists in DB
   ├── If YES: Return existing shortURL
   └── If NO: Continue to step 3
3. Generate unique ID (primary key)
4. Convert ID to base62 → shortURL  
5. Save (ID, shortURL, longURL) to DB
6. Return shortURL to client
```

## 🚀 URL Redirecting Architecture
```
[User] → [Load Balancer] → [Web Servers] → [Cache/DB]
                                     ↓
                              [301/302 Redirect]
```

### Flow:
1. User clicks: `tinyurl.com/2TX`
2. Load balancer routes to web server
3. Check cache for shortURL
4. If cache miss → Query database
5. Return longURL with redirect status
6. Browser redirects to original URL

## 🎯 Key Design Decisions

### 301 vs 302 Redirect Trade-off
| Factor | 301 (Permanent) | 302 (Temporary) |
|--------|----------------|-----------------|
| **Caching** | Browser caches | No browser cache |
| **Server Load** | Lower (cached) | Higher (always hits server) |
| **Analytics** | Limited | Full tracking |
| **Flexibility** | Hard to change | Easy to update |

**Recommendation**: 302 for analytics, 301 for performance

### Performance Optimizations
- **Cache Strategy**: Redis/Memcached for hot URLs
- **Database**: Read replicas for scaling reads  
- **CDN**: Geographic distribution for global users
- **Bloom Filters**: Reduce collision detection queries

## 🔧 Additional Considerations

### Security & Abuse Prevention
- **Rate Limiting**: IP-based request throttling
- **Validation**: URL format and domain checking
- **Monitoring**: Detect malicious patterns

### Scaling Strategies
- **Web Tier**: Stateless, easy horizontal scaling
- **Database**: Read replicas + sharding if needed
- **Cache**: Distributed caching with consistent hashing

### Unique ID Generator Options
- **Database Auto-increment**: Simple but bottleneck
- **UUID**: Guaranteed unique but too long
- **Snowflake**: Distributed ID generation
- **Redis Counter**: Fast, atomic, needs HA setup

## 📈 Analytics Integration
Track metrics like:
- Click rates per URL
- Geographic distribution  
- Time-based patterns
- Referrer sources
- Device/browser stats

Store in separate analytics DB to avoid impacting core performance.

## 🚨 Interview Red Flags to Avoid
- Starting with complex distributed ID generation
- Not considering read/write ratio (10:1)
- Ignoring collision handling in hash approach
- Forgetting about caching for performance
- Over-engineering before establishing basics
