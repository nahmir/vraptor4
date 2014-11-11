package br.com.caelum.vraptor.util;

import java.util.concurrent.Callable;

public abstract class Try<T> {
	public static <T> Try run(Callable<T> callable) {
		try {
			T call = callable.call();
			return new Success(call);
		} catch (Exception e) {
			return new Failed(e);
		}
	}

	public abstract boolean failed();

	public abstract T value();

	public abstract Exception getException();

	public static class Success<T> extends Try {
		private final T result;

		public Success(T result) {
			this.result = result;
		}

		@Override
		public boolean failed() {
			return false;
		}

		@Override
		public Object value() {
			return result;
		}

		@Override
		public Exception getException() {
			throw new UnsupportedOperationException();
		}
	}

	public static class Failed<T> extends Try {
		private final Exception e;

		public Failed(Exception e) {
			this.e = e;
		}

		@Override
		public boolean failed() {
			return true;
		}

		@Override
		public Object value() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Exception getException() {
			return e;
		}
	}


}
