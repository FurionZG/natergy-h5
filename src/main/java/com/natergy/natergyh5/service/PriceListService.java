package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.PriceListMapper;
import com.natergy.natergyh5.entity.Entry;
import com.natergy.natergyh5.entity.Offer;
import com.natergy.natergyh5.utils.ExcelServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @Author: 杨枕戈
 * @Date: 2019/12/6 10:03
 */
@Service
public class PriceListService
{
    @Autowired
    private PriceListMapper priceListDao;
    @Autowired
    private ExcelServer excelServer;
    @Transactional
    public Boolean excelToPng(Offer offer) {
        try {
            savePriceList(offer);
            for(Entry entry:offer.getEntryList()){
                entry.setPrice(entry.getPrice()+"元/千克（含税）");
            }
            this.excelServer.excelToImage(offer);
            return Boolean.valueOf(true);
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }


    public void savePriceList(Offer offer) {
        priceListDao.savePriceList(offer);
        for(Entry entry:offer.getEntryList()){
            entry.setOfferId(offer.getId());
            entry.setStatus("草稿");
            priceListDao.savePriceListDetail(entry);
        }

    }
}
