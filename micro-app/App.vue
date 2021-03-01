<script>
	export default {
		onLaunch: function() {
			uni.HOST_URL = 'https://zsff.sxjiangyan.com'
			uni.H5_URL = 'https://h5.sxjiangyan.com'
			uni.BASE_URL='https://zsff.sxjiangyan.com/index.php/'
			uni.WSS_URL = 'wss://zsff.sxjiangyan.com/wss'
			const BASE_URL = uni.BASE_URL
			uni.request({
				url: BASE_URL+'index/set/base_set',
				method:'GET',
				success:(res) =>{
					console.log(res.data);
					if(res.data.data.title==''){
						res.data.data.title='匠言知识付费'
					}
					uni.setStorageSync('base_set',res.data.data)
				},
				fail:(res)=> {
					console.log(res.data);
				}
			});
			// #ifdef H5
			var set=uni.getStorageSync('base_set')
			var that=this
			var userinfo=uni.getStorageSync('userinfo')
			const HOST_URL=uni.HOST_URL
			
			if(userinfo && userinfo.uid){
				var link=uni.H5_URL+'?pid='+userinfo.uid
			}else{
				var link=uni.H5_URL
			}
			if (this.$jwx && this.$jwx.isWechat()) {
				this.$jwx.share({
					'title': set.share_title,
					'desc': set.share_desc,
					'link': link,
					'img_url': HOST_URL+set.share_icon,
				})
				this.$jwx.sharepyq({
					'title': set.share_title,
					'link': link,
					'img_url': HOST_URL+set.share_icon,
				})
			}
			// #endif
	
			console.warn('当前组件仅支持 uni_modules 目录结构 ，请升级 HBuilderX 到 3.1.0 版本以上！')
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
/* 解决头条小程序组件内引入字体不生效的问题 */
body {
	background-color: #f6f6f6;
}
/* #ifdef H5 */
    uni-page-head {
        display: none;
    }
/* #endif */

</style>
