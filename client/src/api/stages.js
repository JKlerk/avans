import axios from 'axios';
axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

const client = axios.create({
  baseURL: 'http://localhost:8001/api/',
});

export default {
    getStages(params) {
      return client.get('getstages', params);
    },
};