package com.zzlc.wisemana.controller.wemeet;

import com.zzlc.wisemana.dao.EmergencyDrillMapper;
import com.zzlc.wisemana.dao.MeetingEducateMapper;
import com.zzlc.wisemana.dao.MeetingSafeMapper;
import com.zzlc.wisemana.domain.EmergencyDrill;
import com.zzlc.wisemana.domain.MeetingEducate;
import com.zzlc.wisemana.domain.MeetingSafe;
import com.zzlc.wisemana.service.FileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: MeetRecords
 * @Description: TODO(加入录屏文件)
 * @Author shareway.wang
 * @Date 2024/11/28
 */
@RestController
@RequestMapping("conference")
public class MeetRecordsController {
    @Resource
    private EmergencyDrillMapper emergencyDrillMapper;
    @Resource
    private MeetingEducateMapper meetingEducateMapper;
    @Resource
    private MeetingSafeMapper meetingSafeMapper;
    @Resource
    private FileService fileService;

    /**
     * 应急演练插入录屏记录表
     * @return
     */
    @ApiOperation(value = "插入应急演练会议记录", notes = "在上传完录屏文件后调用")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "meetingUuid", value = "唯一会议id号", required = true, dataType = "String"),
        @ApiImplicitParam(name = "fileuuid", value = "被上传文件唯一号", required = true, dataType = "String")
    })
    @PostMapping("insertEmergencyDrillRecord")
    public int insertEmergencyDrillRecord(String meetingUuid, String fileuuid){
        int result = 0;
        EmergencyDrill drill = emergencyDrillMapper.querySingleByMeetingUuid(meetingUuid);
        if (drill != null) {
            fileService.insertFileRelation(28, drill.getId(), 10283, fileuuid);
            return 1;
        }

        return result;
    }

    /**
     * 加入教育培训录屏记录
     * @param meetingUuid
     * @param fileuuid
     * @return
     */
    @ApiOperation(value = "插入教育培训记录", notes = "在上传完录屏文件后调用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "meetingUuid", value = "唯一会议id号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileuuid", value = "被上传文件唯一号", required = true, dataType = "String")
    })
    @PostMapping("insertEducationRecord")
    public int insertEducationRecord(String meetingUuid, String fileuuid){
        int result = 0;
        MeetingEducate meetingEducateInfo = meetingEducateMapper.querySingleByMeetingUuid(meetingUuid);
        if (meetingEducateInfo != null) {
            fileService.insertFileRelation(14, meetingEducateInfo.getId(), 10141, fileuuid);
            return 1;
        }

        return result;
    }


    /**
     * 加入安全生产会议录屏记录
     * @param meetingUuid
     * @param fileuuid
     * @return
     */
    @ApiOperation(value = "插入安全生产记录", notes = "在上传完录屏文件后调用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "meetingUuid", value = "唯一会议id号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileuuid", value = "被上传文件唯一号", required = true, dataType = "String")
    })
    @PostMapping("insertSafeConductRecord")
    public int insertSafeConductRecord(String meetingUuid, String fileuuid){
        int result = 0;
        MeetingSafe meetingSafe = meetingSafeMapper.querySingleByMeetingUuid(meetingUuid);
        if (meetingSafe != null) {
            fileService.insertFileRelation(13, meetingSafe.getId(), 10131, fileuuid);
            return 1;
        }else{
            //bug
        }

        return result;
    }
}
