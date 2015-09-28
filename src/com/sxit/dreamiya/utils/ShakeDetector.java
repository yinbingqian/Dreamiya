package com.sxit.dreamiya.utils;

import java.util.ArrayList;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

/**
 * ���ڼ���ֻ�ҡ��
 * 
 * @author ֣����
 * @see <a href="http://blog.csdn.net/zhengzhiren"
 *      mce_href="http://blog.csdn.net/zhengzhiren">Blog</a>
 */
public class ShakeDetector implements SensorEventListener {
	/**
	 * ����ʱ����
	 */
	static final int UPDATE_INTERVAL = 100;
	/**
	 * ��һ�μ���ʱ��
	 */
	long mLastUpdateTime;
	/**
	 * ��һ�μ��ʱ�����ٶ���x��y��z�����ϵķ��������ں͵�ǰ���ٶȱȽ���
	 */
	float mLastX, mLastY, mLastZ;
	Context mContext;
	SensorManager mSensorManager;
	ArrayList<OnShakeListener> mListeners;
	/**
	 * ҡ�μ����ֵ�������˶�ҡ�ε����г̶ȣ�ԽСԽ���С�
	 */
	public int shakeThreshold = 3500;

	public ShakeDetector(Context context) {
		mContext = context;
		mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		mListeners = new ArrayList<OnShakeListener>();
	}

	/**
	 * ��ҡ���¼�����ʱ������֪ͨ
	 */
	public interface OnShakeListener {
		/**
		 * ���ֻ�ҡ��ʱ������
		 */
		void onShake();
	}

	/**
	 * ע��OnShakeListener����ҡ��ʱ����֪ͨ
	 * 
	 * @param listener
	 */
	public void registerOnShakeListener(OnShakeListener listener) {
		if (mListeners.contains(listener))
			return;
		mListeners.add(listener);
	}

	/**
	 * �Ƴ��Ѿ�ע���OnShakeListener
	 * 
	 * @param listener
	 */
	public void unregisterOnShakeListener(OnShakeListener listener) {
		mListeners.remove(listener);
	}

	/**
	 * ����ҡ�μ��
	 */
	public void start() {
		if (mSensorManager == null) {
			throw new UnsupportedOperationException();
		}
		Sensor sensor = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if (sensor == null) {
			throw new UnsupportedOperationException();
		}
		boolean success = mSensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_GAME);
		if (!success) {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * ֹͣҡ�μ��
	 */
	public void stop() {
		if (mSensorManager != null)
			mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		long currentTime = System.currentTimeMillis();
		long diffTime = currentTime - mLastUpdateTime;
		if (diffTime < UPDATE_INTERVAL)
			return;
		mLastUpdateTime = currentTime;
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		float deltaX = x - mLastX;
		float deltaY = y - mLastY;
		float deltaZ = z - mLastZ;
		mLastX = x;
		mLastY = y;
		mLastZ = z;
		float delta = FloatMath.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
				* deltaZ)
				/ diffTime * 10000;
		if (delta > shakeThreshold) { // �����ٶȵĲ�ֵ����ָ������ֵ����Ϊ����һ��ҡ��
			this.notifyListeners();
		}
	}

	/**
	 * ��ҡ���¼�����ʱ��֪ͨ���е�listener
	 */
	private void notifyListeners() {
		for (OnShakeListener listener : mListeners) {
			listener.onShake();
		}
	}
}