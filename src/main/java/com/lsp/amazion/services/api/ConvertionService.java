package com.lsp.amazion.services.api;

import java.io.IOException;

public interface ConvertionService {

	/**
	 * Converts currency
	 * 
	 * @param value
	 *            Value in original currency
	 * @param from
	 *            the current currency
	 * @param to
	 *            currency to convert to
	 * @return the new value in the to currency
	 * @throws IOException
	 */
	public double convert(double value, String from, String to) throws IOException;
}
