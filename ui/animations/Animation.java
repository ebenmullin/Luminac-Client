package luminac.ui.animations;

import luminac.util.Timer;

public class Animation {
	
	private long animationStart;
    private long animationEnd;
    private double animationFromValue;
    private double animationToValue;
    private double value;

    public double getValue() {
        double path = (double) (System.currentTimeMillis() - animationStart) / (animationEnd - animationStart);

        if(path >= 1.0) {
            return animationToValue;
        } else {
            value = (animationToValue - animationFromValue) * path + animationFromValue;

            return value;
        }
    }

    public void animate(long duration, double toValue) {
        setValue(animationStart);
        animationFromValue = getValue();
        animationStart = System.currentTimeMillis();
        animationEnd = System.currentTimeMillis() + duration;
        animationToValue = toValue;
    }

    public void setValue(double value) {
        this.value = value;
    }
	
}
