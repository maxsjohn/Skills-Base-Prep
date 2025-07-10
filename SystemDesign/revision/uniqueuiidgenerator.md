# Unique ID Generator in Distributed Systems - Revision Notes

## Problem Requirements
- IDs must be **unique**, **numerical**, **64-bit**, **sortable by time**
- Generate **10,000+ IDs per second** in distributed environment

## Four Main Approaches

### 1. Multi-master Replication
- Each DB increments by k (number of servers) instead of 1
- ❌ Hard to scale across data centers, IDs not time-ordered

### 2. UUID
- 128-bit globally unique identifiers
- ❌ Too long (128 vs 64 bits), not time-ordered, can be non-numeric

### 3. Ticket Server
- Centralized auto-increment server
- ❌ Single point of failure

### 4. Twitter Snowflake (Chosen Solution)
- 64-bit ID split into sections:
 - **1 bit**: Sign (always 0)
 - **41 bits**: Timestamp (milliseconds since epoch)
 - **5 bits**: Datacenter ID (32 datacenters max)
 - **5 bits**: Machine ID (32 machines per datacenter)
 - **12 bits**: Sequence number (4,096 IDs per millisecond per machine)

## Key Benefits of Snowflake
- Time-sortable IDs
- Scalable across multiple datacenters/machines
- High throughput (4,096 IDs/ms per machine)
- 69-year lifespan before timestamp overflow

## Important Considerations
- Clock synchronization across servers
- High availability requirements
- Careful management of datacenter/machine IDs to avoid conflicts

## Why Snowflake Wins
The snowflake approach is the winner because it meets all requirements while being scalable and distributed.
