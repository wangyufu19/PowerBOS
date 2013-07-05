package com.framework.common.util;
import java.lang.Runnable;

public abstract class SysThread implements Runnable{
	public abstract void setParam(ThreadParam vo);
	public abstract boolean getStatus();
	public abstract void startRun();
	public abstract void startStop();
}
