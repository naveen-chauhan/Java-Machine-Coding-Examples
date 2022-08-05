# **Problem Statement -**

Design and implement an in-memory Multi Level Cache Management System with N levels, say (L1 -> L2 -> L3 … -> LN).

### **Details:**

##### Input -

Total number of levels in the cache. 
The capacity of each level, i.e. number of elements that can be stored at each level, It can be different at each level.


##### Operations -

**READ** KEY Operation

Read will start from first level. 
If KEY is found at this level, then this value will be returned. 
If KEY is not found at this level, then try to read from the next level. 
Keep doing this until the value of KEY is found at some level or the last level has been reached. 
If the value of KEY is found at some level, then this VALUE should also be written into the first level and the expected write behavior should follow as specified in the next section.

**WRITE** KEY Operation
Writes will start from level L1. 
If a level is full, evict any key-value pair from this level and write the evicted k-v pair into the next level. 
If the next level is also full, evict any k-v pair from that level and insert in the next level and so on. 
If you reach the last level, add a new level and insert the evicted K-V pair in this new level.

**DELETE** KEY operation:
Deletes the given key from all the levels the key is present in.

**Eviction** **Strategy**:
Eviction Strategy should be pluggable. Currently, we are implementing LFU Strategy
