package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.BankAccountId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * 学生银行账户表 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IBankAccountIdService extends IService<BankAccountId> {

    /**
     * 分页获取学生银行账户信息
     *
     * @param page          分页对象
     * @param bankAccountId 学生银行账户信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryBankAccountPage(Page<BankAccountId> page, BankAccountId bankAccountId);
}
