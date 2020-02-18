import axios from 'axios';
axios.defaults.headers.common['Content-Type'] = 'application/json';

const client = axios.create({
  baseURL: 'http://localhost:8001/api/',
});

export default {
    getPerformances(params) {
      return client.get('getperformances', params);
    },
    addPerformance(params) {
      return client.post('addperformance', params);
    },
    editStage(params) {
      return client.post('editstage', params);
    },
    deleteStage(params) {
      return client.post('deletestage', params);
    },
};