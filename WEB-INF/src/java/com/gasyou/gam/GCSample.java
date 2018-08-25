package com.gasyou.gam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GCSample {

	private List<String> caches = new ArrayList<>();

	public static void main(String[] args) {
		new GCSample().run();
	}

	private void run() {
		Scanner scanner = new Scanner(System.in);

		try {
			while (true) {

				System.out.print("Input > ");
				String str = scanner.next();

				if ("exit".equals(str)) {

					// Exit this application.
					break;
				} else if ("gc".equals(str)) {

					// GC.
					System.out.println("GC start!!");

					Runtime.getRuntime().gc();

					System.out.println("GC finished!!");
				} else if ("m".equals(str)) {
					// Show memory
					// Nothing to do.
				} else if ("r".equals(str)) {

					// Reset memory.
					caches = new ArrayList<>();
				} else {
					String volume = new String(new byte[100000000]);
					caches.add(volume);
				}

				// Show memory.
				printMemory();
			}

		} finally {
			scanner.close();

			System.out.println("Exit!!");
		}
	}

	private void printMemory() {
		Runtime r = Runtime.getRuntime();
		System.out.println(String.format("Max: %d, Total: %d, Free: %d",
				r.maxMemory() / 1024 / 1024,
				r.totalMemory() / 1024 / 1024,
				r.freeMemory() / 1024 / 1024));
	}
}
