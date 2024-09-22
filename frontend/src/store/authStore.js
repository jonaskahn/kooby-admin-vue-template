import { defineStore } from 'pinia';
import { computed, ref } from 'vue';
import DEFAULTS from '@/constants/app';

const useAuthStore = defineStore('authStore', () => {
    const expiration = ref();
    const permissions = ref([]);

    const isAuthenticated = computed(() => {
        return expiration.value !== null && expiration.value !== undefined && new Date().getTime() / 1000 < expiration.value;
    });

    const getPermissions = computed(() => permissions.value);

    function updateExpiration() {
        const time = Number(localStorage.getItem(DEFAULTS.ACCESS.EXPIRATION));
        if (!isNaN(time)) {
            expiration.value = time;
        }
    }

    function updatePermission() {
        const permissionRawData = localStorage.getItem(DEFAULTS.ACCESS.PERMISSIONS) ?? '';
        permissions.value = permissionRawData.split(',');
        console.debug(permissions.value);
    }

    return { isAuthenticated, updateExpiration, getPermissions, updatePermission };
});

export default useAuthStore;
