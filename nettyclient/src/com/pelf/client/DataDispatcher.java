package com.pelf.client;

import com.pelf.responsestructure.MarketWatchResponse;

public interface DataDispatcher {
	
	void saveTickData(MarketWatchResponse response);

}
