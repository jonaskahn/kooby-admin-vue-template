const PageSpec = {
    AUTH: {
        LOGIN: {
            path: '/auth/login',
            name: 'login',
            meta: {
                title: 'page.menu-title.auth.login',
                permissions: []
            },
            component: () => import('@/views/pages/auth/Login.vue')
        }
    },
    ACCESS: {
        NOT_FOUND: {
            path: '/pages/notfound',
            name: 'notfound',
            meta: {
                title: 'page.menu-title.page.notfound',
                permissions: []
            },
            component: () => import('@/views/pages/NotFound.vue')
        },
        DENIED: {
            path: '/auth/access',
            name: 'accessDenied',
            meta: {
                title: 'page.menu-title.page.access-denied',
                permissions: []
            },
            component: () => import('@/views/pages/auth/Access.vue')
        },
        ERROR: {
            path: '/auth/error',
            name: 'accessError',
            meta: {
                title: 'page.menu-title.page.access-error',
                permissions: []
            },
            component: () => import('@/views/pages/auth/Error.vue')
        }
    },
    APP: {
        DASHBOARD: {
            path: '/',
            name: 'dashboard',
            meta: {
                title: 'page.menu-title.page.dashboard',
                permissions: []
            },
            component: () => import('@/views/Dashboard.vue')
        },
        PATIENT_RECEPTION: {
            RECEPTION: {
                path: '/patient/reception',
                name: 'reception',
                meta: {
                    title: 'page.menu-title.page.patient-reception',
                    permissions: []
                },
                component: () => import('@/views/reception/PatientReception.vue')
            },
        },
        PAGE: {}
    }
};

export default PageSpec;
