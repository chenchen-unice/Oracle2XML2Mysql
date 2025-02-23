package com.sx.createxml.service;

import com.sx.createxml.dao.repository.MajorDetailRepository;
import com.sx.createxml.dao.repository.MajorPlanningRepository;
import com.sx.createxml.dao.repository.ProjectApplyRepository;
import com.sx.createxml.dao.repository.SubProjectDetailRepository;
import com.sx.createxml.pojo.*;
import com.sx.createxml.pojo.XMLDataStruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sunxu93@163.com
 * @date 19/7/8/008 18:51
 */
@Service
public class SearchThreeTable {

    @Autowired
    MajorPlanningRepository majorPlanningRepository;
    @Autowired
    MajorDetailRepository majorDetailRepository;
    @Autowired
    ProjectApplyRepository projectApplyRepository;
    @Autowired
    SubProjectDetailRepository subProjectDetailRepository;


    public ArrayList<Print4XML> createPrint4XMLList() {
        ArrayList<Print4XML> prints = new ArrayList<>();

        OraclePrintID oraclePrintID = new OraclePrintID();
        int[] printIds = oraclePrintID.getPrintIds();

        for (int i = 0; i < printIds.length; i++) {

            //对每一个oracle中的图纸id都从mysql三个表里找数剧
            Integer idInt = printIds[i];
            Long idL = Long.parseLong(idInt.toString());

            //把数据填入print4XML,用于生成XML

            Print4XML print4XML = new Print4XML();
            print4XML.setPrintId(idInt);

            //从majorplaning拿到mysql三个表的id
            MajorPlanning majorPlanning = majorPlanningRepository.getById(idL);
            Long subProjectId = majorPlanning.getSubProjectId();
            Long projectId = majorPlanning.getProjectId();
            Long majorId = majorPlanning.getMajorId();


            //从majorDetail表找数据
            List<MajorDetail> allByProjectId = majorDetailRepository.findAllByProjectId(majorId);
            MajorDetail majorDetail = allByProjectId.get(0);

            //从projectApply表找数据
            ProjectApply projectApply = projectApplyRepository.getById(projectId);

            //从subProjectDetail表找数据
            List<SubProjectDetail> byProjectId = subProjectDetailRepository.findByProjectId(subProjectId);
            SubProjectDetail subProjectDetail = byProjectId.get(0);


            //填文件基本元数据
            FileBasic fileBasic = print4XML.getFileBasic();

            fileBasic.fillFileBasic(majorPlanning, majorDetail, subProjectDetail, projectApply);

            //填业务基本元数据
            ServiceBasic serviceBasic = print4XML.getServiceBasic();

            serviceBasic.fillFileBasic(majorPlanning, majorDetail, subProjectDetail, projectApply);

            //填写图纸信息元数据
            PrintInfoBasic printInfoBasic = print4XML.getPrintInfoBasic();
            printInfoBasic.fillFileBasic(majorPlanning, majorDetail, subProjectDetail, projectApply);
            //填写行为元数据
            ProcessBasic processBasic = print4XML.getProcessBasic();
            processBasic.fillFileBasic(majorPlanning, majorDetail, subProjectDetail, projectApply);

            //把填好的print放进prints(list)
            prints.add(print4XML);


        }
        return prints;
    }


}
