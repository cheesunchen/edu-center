<template>
	<view class="container">
		<!-- 计算手机状态栏的高度 -->
		<!-- #ifndef MP-WEIXIN || MP-BAIDU || MP-TOUTIAO || MP-ALIPAY -->
		<!-- #endif -->
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
				<swiper :indicator-dots="true" :autoplay="true" :interval="2000" :duration="1000" circular="true" @change="swiper_change">
					<swiper-item v-for="item in images" :key="index">
						<view class="swiper-item">
							<image :src="item" mode="aspectFit"></image>
						</view>
					</swiper-item>
				</swiper>
			</view>
			<!-- 图标 -->
			<view class="icons">

				<view class="icons-list" @click="postSearchCourse('course')">
					<image src="@/static/icon/daoshi.png" mode="aspectFit"></image>
					<text>课程</text>
				</view>
				<view class="icons-list" @click="postSearchCourse('instrument')">
					<image src="@/static/icon/hot.png" mode="aspectFit"></image>
					<text>器材</text>
				</view>
				<view class="icons-list" @click="navigate('sign-in')">
					<image src="@/static/icon/qiandao.png" mode="aspectFit"></image>
					<text>签到</text>
				</view>
				<view class="icons-list" @click="navigate('sign-in')">
					<image src="@/static/icon/qiandao.png" mode="aspectFit"></image>
					<text>充值</text>
				</view>
			</view>

			<!-- 课程专场 -->
			<view class="course">
				<view class="course-top">
					<image src="@/static/icon/kczc.png" mode="aspectFit"></image>
					<text class="btn" @click="navigate('course-list','indextj')">查看更多></text>
				</view>
				<view class="course-bottom">
					<view class="course-bottom-list" v-for="(item, index) in index_courseTJ" :key="index" @click="goCourseDetails(item.id)">
						<image :src="item.cover" mode="aspectFit"></image>
						<text>{{item.name}}</text>
						<text>共{{item.courseAmount}}课时<text>|</text>学费{{item.price}}元</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		getIndexCourse,
		getIndexBanner,
		getIndexInfo,
		getIndexListInfo,
		postIndexGoods,
		postDhHyp
	} from '@/request/index'
	import {
		wx_login
	} from '@/request/checkUserinfo'
	export default {
		data() {
			return {
				keyword: '',
				navBarFixed: false,
				images: [],
				HOST_URL: uni.HOST_URL,

				index_courseTJ: [], //主页课程推荐
				flid: '', // 这个变量用于传值
			}
		},
		onShow() {
			this.getIndexInfo()
		},
		methods: {
			swiper_change(e) {
				this.swiper_bgcolor = this.images[e.detail.current].maincolor
			},
			// 获取轮播图和列表信息
			getIndexInfo() {
				getIndexCourse().then(res => {
					if (res.statusCode === 200) {
						if(res.data.status === 200 && res.data.data) {
							this.index_courseTJ = res.data.data.list
						}
					}
				})
				getIndexBanner().then(res => {
					if(res.statusCode === 200) {
						if(res.data.status === 200 && res.data.data) {
							this.images = res.data.data
						}
					}
				})
			},

			// 页面跳转
			navigate(e, param) {
				if (e == 'vip') {
					uni.switchTab({
						url: '/pages/' + e + '/' + e
					})
				} else if (e == 'course-list') {
					uni.navigateTo({
						url: '/pages/' + e + '/' + e + '?moretype=' + param
					})
				} else {
					uni.navigateTo({
						url: '/pages/' + e + '/' + e
					})
				}
			},
			postSearchCourse(e) {
				var keyword
				if (e == 'course' || e == 'instrument' || e == 'free') {
					keyword = e
				} else {
					keyword = this.keyword
					if (keyword == '') {
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
	@import url('./goods-course.less');

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
