import NotificationService from '@/service/NotificationService';
import { http, httpSecure, ResponseType } from '@/common/http';
import router from '@/router';
import PageSpec from '@/router/page';

export default class BaseService {
    static #showMessage(res, notifySuccess, notifyError) {
        let message = res.status === ResponseType.UNDEFINED ? 'service.default-message.unknown-error' : res.message;
        if (notifySuccess && res.status === ResponseType.SUCCESS) {
            this.showSuccessMessage(message);
            return;
        }
        if (notifyError && res.status !== ResponseType.SUCCESS) {
            this.showErrorMessage(message);
        }
    }

    static showErrorMessage(message) {
        NotificationService.INSTANCE.sendErrorMessage({
            body: message
        }).then();
    }

    static showSuccessMessage(message) {
        NotificationService.INSTANCE.sendSuccessMessage({
            body: message
        }).then();
    }

    static #handleResponse(res, option) {
        BaseService.#showMessage(res, option.notifyOnSuccess, option.notifyOnError);
        if (res.status === ResponseType.UNAUTHORIZED) {
            return router.push({
                name: PageSpec.AUTH.LOGIN.name
            });
        }
        if (res.status === ResponseType.ACCESS_DENIED) {
            return router.push({
                name: PageSpec.ACCESS.DENIED.name
            });
        }
        if ((option.redirectOnError ?? false) && res.status !== ResponseType.SUCCESS) {
            return router.push({
                name: PageSpec.ACCESS.ERROR.name
            });
        }
        return Promise.resolve({
            state: res.status === ResponseType.SUCCESS,
            payload: res.payload
        });
    }

    async request(
        spec = {
            path: '',
            method: 'GET',
            data: null
        },
        options = {
            secure: true,
            notifyOnSuccess: false,
            notifyOnError: true,
            redirectOnError: false
        }
    ) {
        if (options.secure) {
            return this.#requestAuth(spec, options);
        } else {
            return this.#requestNoAuth(spec, options);
        }
    }

    async #requestNoAuth(
        spec = {
            path: '',
            method: 'GET',
            data: null
        },
        options = {
            notifyOnSuccess: false,
            notifyOnError: true,
            redirectOnError: false
        }
    ) {
        const res = await http.request({
            path: spec.path,
            method: spec.method,
            data: spec.data
        });

        return BaseService.#handleResponse(res, options);
    }

    async #requestAuth(
        spec = {
            path: '',
            method: 'GET',
            data: null
        },
        options = {
            notifyOnSuccess: false,
            notifyOnError: true,
            redirectOnError: false
        }
    ) {
        const res = await httpSecure.request({
            path: spec.path,
            method: spec.method,
            data: spec.data
        });

        return BaseService.#handleResponse(res, options);
    }
}