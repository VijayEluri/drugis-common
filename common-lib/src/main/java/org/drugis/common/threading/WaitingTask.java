package org.drugis.common.threading;

import org.drugis.common.threading.event.ListenerManager;

public abstract class WaitingTask implements Task {
	protected ListenerManager d_mgr = new ListenerManager(this);
	protected boolean d_started = false;
	protected boolean d_finished = false;

	/**
	 * Whether the task should currently be waiting.
	 */
	public abstract boolean isWaiting();
	
	/**
	 * To be executed when the task starts waiting. Should not perform intensive computation.
	 */
	public void onStartWaiting() {
		d_started = true;
		d_mgr.fireTaskStarted();
	}
	
	/**
	 * To be executed when the task is done waiting. Should not perform intensive computation.
	 */
	public void onEndWaiting() {
		d_started = false;
		d_finished = true;
		d_mgr.fireTaskFinished();
	}

	public boolean isStarted() {
		return d_started;
	}

	public boolean isFinished() {
		return d_finished;
	}

	public boolean isFailed() {
		return false;
	}

	public Throwable getFailureCause() {
		return null;
	}

	public boolean isAborted() {
		return false;
	}
	
	public void addTaskListener(TaskListener l) {
		d_mgr.addTaskListener(l);
	}

	public void removeTaskListener(TaskListener l) {
		d_mgr.removeTaskListener(l);
	}
}
