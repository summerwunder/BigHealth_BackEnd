package edu.whut.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whut.domain.dto.BannerDTO;
import edu.whut.domain.pojo.Banner;
import edu.whut.response.Result;
import edu.whut.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping("/add")
    public Result addBanner(@RequestBody BannerDTO banner) {
        boolean success = bannerService.addBanner(banner);
        if (success) {
            return Result.success("新增轮播图成功");
        } else {
            return Result.error("新增轮播图失败");
        }
    }

    @GetMapping("/list")
    public Result getBannerList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String position) {
        IPage<Banner> bannerPage = bannerService.getBannerList(page, size, position);
        return Result.success(bannerPage);
    }

    /**
     * 根据 ID 删除轮播图
     *
     * @param id 轮播图 ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Result deleteBannerById(@PathVariable Long id) {
        boolean success = bannerService.deleteBannerById(id);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败，ID 不存在或已删除");
        }
    }

    @GetMapping("/details/{id}")
    public Result getBannerDetails(@PathVariable Long id) {
        Banner banner = bannerService.getById(id);
        if (banner == null || banner.getIsDeleted() == 1) {
            return Result.error("轮播图不存在或已删除");
        }
        return Result.success(banner);
    }

}
