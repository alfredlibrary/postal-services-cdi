/*
 * Alfred Library.
 * Copyright (C) 2011 Alfred Team
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.alfredlibrary.postalservices.internal.tracking;

import javax.inject.Inject;

import junit.framework.Assert;

import org.alfredlibrary.postalservices.tracking.TrackingInfo;
import org.alfredlibrary.postalservices.tracking.annotation.Correios;
import org.alfredlibrary.postalservices.tracking.exception.IncorrectTrackingCodeException;
import org.alfredlibrary.postalservices.tracking.exception.NullOrEmptyTrackingCodeException;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

/**
 * Testing Correios Tracking implementation.
 * 
 * @author Marlon Silva Carvalho
 * @since 2.0.0
 */
@RunWith(DemoiselleRunner.class)
public class CorreiosCDITrackingTest {

	@Inject
	@Correios
	private CorreiosTracking tracking;

	@Test(expected = IncorrectTrackingCodeException.class)
	public void testIncorrectCode() {
		tracking.track("PB4");
	}

	@Test(expected = NullOrEmptyTrackingCodeException.class)
	public void testFailWithNullCode() {
		tracking.track(null);
	}

	@Test(expected = NullOrEmptyTrackingCodeException.class)
	public void testFailWithEmptyCode() {
		tracking.track("");
	}

	@Test
	public void testSuccessCode() {
		TrackingInfo info = tracking.track("RM050887654IN");
		Assert.assertEquals("AC CONCHAS - CONCHAS/SP", info.getStatuses().get(0).getCity());
		Assert.assertEquals("Entregue", info.getStatuses().get(0).getDescription());
	}

}