<template>
	<view class="container">
		<!-- 计算手机状态栏的高度 -->
		<!-- #ifndef MP-WEIXIN || MP-BAIDU || MP-TOUTIAO || MP-ALIPAY -->
		<!-- 头部 -->
		<view class="header">		
			<!-- 搜索框 -->
			<view class="header-input">
				<image @click="postSearchCourse" class="search" src="@/static/icon/search.png" mode="aspectFit"></image>
				<input v-model.trim="keyword" class="seader-input" :class="{newinput: navBarFixed}" type="text" placeholder="请输入搜索内容" />
			</view>
		</view>
		<view>
			<!-- 轮播图 -->
			<view class="swiper">
				<swiper :indicator-dots="true"
				:autoplay="true"
				:interval="2000"
				:duration="1000"
				circular="true" @change="swiper_change">
					<swiper-item v-for="item in images" :key="item.id">
						<view class="swiper-item">
							<image :src="HOST_URL + item.thumb" mode="aspectFit"></image>
						</view>
					</swiper-item>
				</swiper>
			</view>
			<!-- 图标 -->
						<view class="icons">
			
							<view class="icons-list" @click="postSearchCourse('course')">
								<image src="@/static/daoshi.png" mode="aspectFit"></image>
								<text>课程</text>
							</view>
							<view class="icons-list" @click="postSearchCourse('instrument')">
								<image src="@/static/hot.png" mode="aspectFit"></image>
								<text>器材</text>
							</view>
							<view class="icons-list" @click="navigate('sign-in')">
								<image src="@/static/qiandao.png" mode="aspectFit"></image>
								<text>签到</text>
							</view>
							<view class="icons-list" @click="navigate('sign-in')">
								<image src="@/static/qiandao.png" mode="aspectFit"></image>
								<text>充值</text>
							</view>
						</view>
		</view>
	</view>
</template>

<script>
	import {getIndexInfo, getIndexListInfo, postIndexGoods, postDhHyp} from '@/request/index'
	import {wx_login} from '@/request/checkUserinfo'
	export default {
		data() {
			return {
				keyword: '',
				navBarFixed: false,
				huifu: false,
				images: [],
				HOST_URL: uni.HOST_URL
			}
		},
		onShow() {
			console.log('onShow')
			this.getIndexInfo()
			// 计时关闭弹窗
			var date = new Date()
			var nowtime=date.getTime()
			let base_set=uni.getStorageSync('base_set')
			let adclosetime=uni.getStorageSync('adclosetime')
			console.log(nowtime+'...'+adclosetime);
			if(base_set){
				this.base_set=base_set
				if(base_set.is_gz==1){
					if(adclosetime){
						if(nowtime-adclosetime>8000){
							this.ad_close=true
						}else{
							this.ad_close=false
						}
					}else{
						this.ad_close=true
					}
				}
			}
			// #ifdef H5
			var userinfo=uni.getStorageSync('userinfo')
			if(!userinfo && !userinfo.userdata){
				wx_login()
			}
			// #endif
		},
		methods: {
			swiper_change(e){
				console.log('')
				this.swiper_bgcolor=this.images[e.detail.current].maincolor
			},
			// 获取轮播图和列表信息
			getIndexInfo() {
				getIndexInfo().then(res => {
					console.log('res')
					this.images = res.data.data.ads
					this.fls = res.data.data.classify
					this.index_courseTJ=res.data.data.courses
					console.log(this.images)
				})
			},

			// 页面跳转
			navigate(e,param){
				if(e=='vip'){
					uni.switchTab({
						url:'/pages/'+e+'/'+e
					})
				}else if(e=='course-list'){
					uni.navigateTo({
						url:'/pages/'+e+'/'+e+'?moretype='+param
					})
				}
				else {
					uni.navigateTo({
						url:'/pages/'+e+'/'+e
					})
				}
			},
			postSearchCourse(e) {
				var keyword
				if(e == 'course' || e == 'instrument' || e == 'free' ) {
					keyword = e
				} else {
					keyword = this.keyword
					if(keyword == '') {
						uni.showToast({
							title: '搜索内容不能为空',
							icon: 'none'
						});
						return false
					}
				}
				uni.navigateTo({
					url: `/pages/shop/shop?keyword=${keyword}`
				});
			},
			
		}
	}
</script>

<style lang="less">
@import url('./home.less');
@keyframes rotation {
	from {
		transform: rotate(0deg);
	}
	to {
		transform: rotate(720deg);
	}
}
.an {
	animation: rotation 2s infinite linear;
}
.pause {
	animation-play-state: paused;
}
</style>
