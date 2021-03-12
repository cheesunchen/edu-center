<script>
	export default {
		onLaunch: function() {
			uni.HOST_URL = 'https://zsff.sxjiangyan.com'
			uni.H5_URL = 'https://h5.sxjiangyan.com'
			uni.BASE_URL='https://zsff.sxjiangyan.com/index.php/'
			uni.APP_URL='http://www.cheesun.com:8088'
			uni.WSS_URL = 'wss://zsff.sxjiangyan.com/wss'
		},
		onShow: function() {
			
		},
		onHide: function() {
			
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
