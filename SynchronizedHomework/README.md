Imagine 2 threads calling getLogger() 
Thread T1
- Checks l == null → true
- Acquires the lock
- Checks l == null again → true
- Creates the Logger
- Releases the lock


Thread T2
- Checks l == null → true
- Waits for the lock
- Acquires the lock after T1
- Checks l == null again → false
- Returns the existing Logger