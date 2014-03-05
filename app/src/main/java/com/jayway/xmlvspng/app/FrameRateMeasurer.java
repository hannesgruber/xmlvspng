package com.jayway.xmlvspng.app;

import java.util.ArrayList;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class FrameRateMeasurer {

	private boolean measuring;
	private long lastFrameTime = SystemClock.elapsedRealtime();
	private final ArrayList<Double> frameRates = new ArrayList<Double>();
	private final String id;
	private final int size;

	/**
	 * creates a new {@link com.jayway.xmlvspng.app.FrameRateMeasurer}. The id will be used in the logs
	 * to identify the measurer.
	 * 
	 * @param id
	 *            the id of the {@link com.jayway.xmlvspng.app.FrameRateMeasurer}
	 */
	public FrameRateMeasurer(String id) {
		this(id, 0);
	}
	
	public FrameRateMeasurer(String id, int size) {
		this.id = id;
		this.size = size;
	}

	/**
	 * starts framerate measuring, i.e. calls to measureFrame() will calculate
	 * and store framerate unitl stopMeasuring() is called.
	 */
	public void startMeasuring() {
		measuring = true;
	}

	/**
	 * measures the framerate by comparing the time of drawing this frame with
	 * the last.
	 */
	public void measureFrame(Context context) {
		if (measuring) {
			long frameTime = SystemClock.elapsedRealtime();
			frameRates.add(1000 / (frameTime - (double) lastFrameTime));
			lastFrameTime = frameTime;
			
			if(size > 0 && frameRates.size() == size){
				stopMeasuring(context);
				startMeasuring();
			}
		}
	}

	/**
	 * stops measuring and logs the results (average and individual framerates).
	 */
	public void stopMeasuring(Context context) {
		if (frameRates.size() > 0) {
			long sum = 0;
			for (Double d : frameRates) {
				sum += d;
			}
			Log.d("XMLvsPNG", id + " average fps:" + sum / frameRates.size());
            if(context != null){
                Toast.makeText(context, "Average fps:" + sum / frameRates.size(), Toast.LENGTH_SHORT).show();
            }
		}
		measuring = false;
		frameRates.clear();
	}

    public void stopMeasuring() {
        stopMeasuring(null);
    }
}
