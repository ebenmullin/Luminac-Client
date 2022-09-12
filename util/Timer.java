package luminac.util;

public class Timer {

    public long lastMS = System.currentTimeMillis();

    public void reset() {
        lastMS = System.currentTimeMillis();
    }

    public boolean hasTimeElapsed(double d, boolean reset) {
        if (System.currentTimeMillis() - lastMS > d) {
            if (reset)
                reset();


            return true;
        }

        return false;
    }

    public long getTime() {
        return System.currentTimeMillis() - lastMS;
    }

    public void setTime(long time) {
        lastMS = time;
    }
}