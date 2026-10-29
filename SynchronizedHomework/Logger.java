// The shared instance must be volatile for double-checked locking.
private static volatile Logger l;

public static Logger getLogger() {
    if (l == null) {
        synchronized (Logger.class) {
            if (l == null) {
                l = new Logger();
            }
        }
    }
    return l;
}
