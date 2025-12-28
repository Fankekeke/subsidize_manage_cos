package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ChatRecord;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IChatRecordService;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/chat-record")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatRecordController {

    private final IChatRecordService chatRecordService;

    private final IStaffInfoService staffInfoService;

    private final IStudentInfoService studentInfoService;

    /**
     * 分页查询聊天记录
     *
     * @param page       分页对象
     * @param chatRecord 聊天记录
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ChatRecord> page, ChatRecord chatRecord) {
        return R.ok(chatRecordService.page(page, Wrappers.<ChatRecord>lambdaQuery()
                .eq(chatRecord.getUserId() != null, ChatRecord::getUserId, chatRecord.getUserId())
                .eq(chatRecord.getStaffId() != null, ChatRecord::getStaffId, chatRecord.getStaffId())
                .orderByDesc(ChatRecord::getCreateTime)));
    }

    /**
     * 根据导员ID获取沟通联系人列表
     *
     * @param staffId 导员ID
     * @return 联系人列表
     */
    @GetMapping("/contacts/staff/{staffId}")
    public R getContactsByStaffId(@PathVariable Integer staffId) {
        StaffInfo staffInfo = staffInfoService.getOne(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getUserId, staffId));
        return R.ok(chatRecordService.getContactsByStaffId(staffInfo.getId()));
    }

    /**
     * 根据用户ID获取沟通联系人列表
     *
     * @param userId 用户ID
     * @return 联系人列表
     */
    @GetMapping("/contacts/user/{userId}")
    public R getContactsByUserId(@PathVariable Integer userId) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, userId));
        return R.ok(chatRecordService.getContactsByUserId(studentInfo.getId()));
    }

    /**
     * 根据用户ID和导员ID获取聊天记录
     *
     * @param userId  用户ID
     * @param staffId 导员ID
     * @return 结果
     */
    @GetMapping("/list")
    public R getListByUserAndHotel(@RequestParam Integer userId, @RequestParam Integer staffId) {
        List<ChatRecord> list = chatRecordService.list(Wrappers.<ChatRecord>lambdaQuery()
                .eq(ChatRecord::getUserId, userId)
                .eq(ChatRecord::getStaffId, staffId)
                .orderByAsc(ChatRecord::getCreateTime));
        return R.ok(list);
    }

    /**
     * 发送消息
     *
     * @param chatRecord 聊天记录
     * @return 结果
     */
    @PostMapping
    public R sendMsg(ChatRecord chatRecord) {
        chatRecord.setCreateTime(DateUtil.formatDateTime(new Date()));
        return R.ok(chatRecordService.save(chatRecord));
    }

    /**
     * 发送消息
     *
     * @param chatRecord 聊天记录
     * @return 结果
     */
    @PostMapping("/defaultChat")
    public R defaultChat(ChatRecord chatRecord) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, chatRecord.getUserId()));
        chatRecord.setUserId(studentInfo.getId());
        chatRecord.setCreateTime(DateUtil.formatDateTime(new Date()));
        return R.ok(chatRecordService.save(chatRecord));
    }

    /**
     * 发送消息
     *
     * @param chatRecord 聊天记录
     * @return 结果
     */
    @PostMapping("/defaultStaffChat")
    public R defaultStaffChat(ChatRecord chatRecord) {
        StaffInfo staffInfo = staffInfoService.getOne(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getUserId, chatRecord.getStaffId()));
        chatRecord.setStaffId(staffInfo.getId());
        chatRecord.setCreateTime(DateUtil.formatDateTime(new Date()));
        return R.ok(chatRecordService.save(chatRecord));
    }

    /**
     * 标记消息为已读
     *
     * @param id 消息ID
     * @return 结果
     */
    @PutMapping("/read/{id}")
    public R markAsRead(@PathVariable Integer id) {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId(id);
        chatRecord.setStatus("1");
        return R.ok(chatRecordService.updateById(chatRecord));
    }
}