import BaseService from '@/service/BaseService';
import API from '@/constants/api';

export default class PatientRequestService extends BaseService {
    static INSTANCE =  new PatientRequestService()

    async getPatientRequests(params){
        const res = await this.request(
            {
                path: '/secure/patientRequest/search',
                method: 'POST',
                data: params
            },
            {
                secure: true,
                notifyOnSuccess: false,
                notifyOnError: true
            }
        )
        return res.payload
    }
}
