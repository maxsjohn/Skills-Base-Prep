# URL Shortener - Revision Notes

## Problem Requirements
- **URL shortening**: Long URL → Short URL
- **URL redirecting**: Short URL → Original URL  
- **Scale**: 100M URLs/day, 10:1 read/write ratio
- **Characters**: 0-9, a-z, A-Z (62 total)
- **Storage**: 36.5TB over 10 years

## Back of Envelope Calculations
- **Write**: 100M URLs/day = 1,160/sec
- **Read**: 1,160 × 10 = 11,600/sec
- **Total URLs**: 365 billion over 10 years
- **Storage**: 365B × 100 bytes = 36.5TB

## API Design
```
POST /api/v1/data/shorten
Body: {longUrl: "https://..."}
Return: shortURL

GET /api/v1/shortUrl
Return: HTTP 301/302 redirect to longURL
```

## Redirect Types
- **301 Redirect**: Permanent, browser caches (reduces server load)
- **302 Redirect**: Temporary, always hits server (better for analytics)

## Data Model
Simple 3-column table:
- **id** (Primary Key)
- **shortURL** 
- **longURL**

## Hash Function Design

### Hash Value Length
- 62 possible characters [0-9, a-z, A-Z]
- Need: 62^n ≥ 365 billion
- **Solution**: n = 7 (62^7 = 3.5 trillion URLs)

### Two Approaches

#### 1. Hash + Collision Resolution
- Use CRC32/MD5/SHA-1, take first 7 chars
- ❌ Hash collisions need resolution
- ❌ Expensive DB lookups for collision checking
- ✅ Fixed URL length
- ✅ No dependency on unique ID generator

#### 2. Base 62 Conversion (Chosen)
- Convert unique ID to base 62
- Example: ID 11157 → base 62 "2TX"
- ✅ No collisions (unique IDs)
- ✅ Simple conversion
- ❌ Variable URL length
- ❌ Requires unique ID generator
- ❌ Predictable URLs (security concern)

## URL Shortening Flow
1. Input: longURL
2. Check if longURL exists in DB
3. If exists → return existing shortURL
4. If new → generate unique ID
5. Convert ID to shortURL (base 62)
6. Save (ID, shortURL, longURL) to DB

## URL Redirecting Flow
1. User clicks shortURL
2. Load balancer forwards to web servers
3. Check cache for shortURL
4. If not cached → fetch from database
5. Return longURL with HTTP redirect

## System Architecture
- **Load Balancer** → distributes traffic
- **Web Servers** → stateless, easy to scale
- **Cache** → stores popular shortURL mappings
- **Database** → persistent storage with replication/sharding

## Additional Considerations
- **Rate Limiting**: Prevent abuse
- **Analytics**: Track clicks, timing, sources
- **Database Scaling**: Replication + sharding
- **High Availability**: Multiple data centers
- **Security**: Rate limiting, input validation

## Why Base 62 Conversion Wins
- Simplicity and no collision handling
- Leverages existing unique ID generator
- Predictable performance
- Easy to implement and maintain
