import request from '../utils/request';

export const fetchData = query => {
    return request({
        url: './table.json',
        method: 'get',
        params: query
    });
};

export const testApi = query => {
    return request({
        url: '/api/hello',
        method: 'POST',
        params: query
    });
};

export const getCoursePage = query => {
    return request({
        url: '/api/course/getCoursePage',
        method: 'POST',
        data: query
    });
};

export const getBannerPage = query => {
    return request({
        url: '/api/system/getBannerPage',
        method: 'POST',
        data: query
    });
};

export const saveBannerInfo = query => {
    return request({
        url: '/api/system/saveBannerInfo',
        method: 'POST',
        data: query
    });
};
