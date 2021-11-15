package com.shaprj.test.iocframework.business;

import com.shaprj.test.iocframework.core.annotation.Injectable;

@Injectable
public class ToyotaAdvertisementImpl implements Advertisement {
    @Override
    public void printAd(String adText) {
        System.out.println(adText);
    }
}
