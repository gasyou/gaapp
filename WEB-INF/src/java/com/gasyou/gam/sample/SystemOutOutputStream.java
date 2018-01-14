package com.gasyou.gam.sample;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class SystemOutOutputStream extends ServletOutputStream {

	private ServletOutputStream output;

	public SystemOutOutputStream(ServletOutputStream output) {
		this.output = output;
	}

	@Override
	public void write(int b) throws IOException {
		output.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		System.out.println(new String(b));
		output.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		output.write(b, off, len);
	}

	@Override
	public boolean isReady() {
		return output.isReady();
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		output.setWriteListener(arg0);
	}
}