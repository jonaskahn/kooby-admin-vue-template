import BaseService from '@/service/BaseService';
import DEFAULTS from '@/constants/app';
import useAuthStore from '@/store/authStore';
import API from '@/constants/api';

const authStore = useAuthStore();

export default class AuthService extends BaseService {
    static INSTANCE = new AuthService();

    async login(params) {
        const res = await this.request(
            {
                path: API.AUTH.GENERATE_TOKEN,
                method: 'POST',
                data: params
            },
            {
                secure: false,
                notifyOnSuccess: true,
                notifyOnError: true
            }
        );
        if (res.state) {
            this.#updateAccessTokenInfo(res.payload);
        }
        return res.state;
    }

    #updateAccessTokenInfo(data) {
        localStorage.setItem(DEFAULTS.ACCESS.TOKEN, data);

        const token = JSON.parse(atob(data.split('.')[1]));
        localStorage.setItem(DEFAULTS.ACCESS.EXPIRATION, token['exp']);
        localStorage.setItem(DEFAULTS.ACCESS.PERMISSIONS, token['$int_roles']);
        authStore.updateExpiration();
        authStore.updatePermission();
    }
}
