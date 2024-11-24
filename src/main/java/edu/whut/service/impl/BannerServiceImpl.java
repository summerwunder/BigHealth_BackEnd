package edu.whut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whut.domain.dto.BannerDTO;
import edu.whut.domain.pojo.Banner;
import edu.whut.service.BannerService;
import edu.whut.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author wunder
* @description 针对表【banner(轮播图管理表)】的数据库操作Service实现
* @createDate 2024-11-23 21:26:50
*/
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner>
    implements BannerService{

    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public boolean addBanner(BannerDTO banner) {
        Banner bannerEntity = new Banner();
        bannerEntity.setPosition(banner.getPosition());
        bannerEntity.setImageUrl(banner.getImage());
        bannerEntity.setSortOrder(banner.getSortOrder());
        bannerEntity.setCreateTime(new Date());
        bannerEntity.setUpdateTime(new Date());
        int rows = bannerMapper.insert(bannerEntity);
        return rows > 0;
    }

    @Override
    public IPage<Banner> getBannerList(int page, int size, String position) {
        // 分页对象
        IPage<Banner> pageRequest = new Page<>(page, size);

        // 构建查询条件
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();
        if (position != null && !position.trim().isEmpty()) {
            queryWrapper.eq(Banner::getPosition, position);
        }
        queryWrapper.orderByDesc(Banner::getSortOrder);

        // 查询分页数据
        return bannerMapper.selectPage(pageRequest, queryWrapper);
    }

    @Override
    public boolean deleteBannerById(Long id) {
        Banner banner = bannerMapper.selectById(id);
        if (banner == null || banner.getIsDeleted() == 1) {
            return false; // 轮播图不存在或已删除
        }
        return bannerMapper.deleteById(id) > 0;
    }



}




