package com.bogie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogie.common.bus.CommonService;
import com.bogie.common.lib.model.Stat;

@RestController
public class StatController
{
    @Autowired
    private CommonService   commonService;

    public StatController()
    {
    }
    
    @RequestMapping("/stat")
    public Stat getStat(@RequestParam(value="id", required=true) Long id)
    {
        return commonService.getStat(id);
    }
    
    @RequestMapping("/stat/update")
    public Stat updateStat(@RequestBody Stat stat)
    {
        return commonService.saveStat(stat);
    }
    
    @RequestMapping("/stat/delete")
    public void deleteStat(@RequestParam(value="id", required=true) Long id)
    {
        commonService.deleteStat(id);
    }
    
    @RequestMapping("/stat/list")
    public List<Stat> getStats()
    {
        return commonService.findStats();
    }
}
