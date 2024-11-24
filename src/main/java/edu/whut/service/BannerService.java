package edu.whut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.domain.dto.BannerDTO;
import edu.whut.domain.pojo.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wunder
* @description 针对表【banner(轮播图管理表)】的数据库操作Service
* @createDate 2024-11-23 21:26:50
*/
public interface BannerService extends IService<Banner> {

    boolean addBanner(BannerDTO banner);

    IPage<Banner> getBannerList(int page, int size, String position);

    boolean deleteBannerById(Long id);
}
