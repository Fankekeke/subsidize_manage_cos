package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.BankAccountIdMapper;
import cc.mrbird.febs.cos.entity.BankAccountId;
import cc.mrbird.febs.cos.service.IBankAccountIdService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 学生银行账户表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class BankAccountIdServiceImpl extends ServiceImpl<BankAccountIdMapper, BankAccountId> implements IBankAccountIdService {

    /**
     * 分页获取学生银行账户信息
     *
     * @param page          分页对象
     * @param bankAccountId 学生银行账户信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryBankAccountPage(Page<BankAccountId> page, BankAccountId bankAccountId) {
        return baseMapper.queryBankAccountPage(page, bankAccountId);
    }
}
