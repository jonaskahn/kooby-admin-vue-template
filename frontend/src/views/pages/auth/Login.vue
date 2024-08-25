<script setup>
import FloatingConfigurator from '@/components/FloatingConfigurator.vue';
import { inject, ref } from 'vue';
import AuthService from '@/service/AuthenService';
import SETTINGS from '@/constants/settings';
import PageSpec from '@/router/page';
import { useRouter } from 'vue-router';

const email = ref('');
const password = ref('');
const checked = ref(false);

const router = useRouter();

const $loading = inject('$loading');
const login = async () => {
    const loader = $loading.show(SETTINGS.LOADING_PROPERTIES);
    let result = false;
    try {
        result = await AuthService.INSTANCE.login({
            email: email.value,
            password: password.value
        });
        if (result) {
            await router.push({
                name: PageSpec.APP.DASHBOARD.name
            });
        }
    } finally {
        setTimeout(() => loader.hide(), 500);
    }
};
</script>

<template>
    <div>
        <FloatingConfigurator />
        <div class="bg-surface-50 dark:bg-surface-950 flex items-center justify-center min-h-screen min-w-[100vw] overflow-hidden">
            <div class="flex flex-col items-center justify-center">
                <div style="border-radius: 56px; padding: 0.3rem; background: linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)">
                    <div class="w-full bg-surface-0 dark:bg-surface-900 py-20 px-8 sm:px-20" style="border-radius: 53px">
                        <div class="flex flex-col text-center items-center justify-center mb-8">
                            <img src="/demo/images/logo-white.png" height="71" width="326" class="mx-auto" />
                            <span class="mt-4 text-muted-color font-medium">{{$tt('login.label.continue')}}</span>
                        </div>

                        <div>
                            <label class="block text-surface-900 dark:text-surface-0 text-xl font-medium mb-2" for="email1">{{$tt('login.label.email')}}</label>
                            <InputText id="email1" v-model="email" class="w-full md:w-[30rem] mb-8" placeholder="Email address" type="text" />

                            <label class="block text-surface-900 dark:text-surface-0 font-medium text-xl mb-2" for="password1">{{$tt('login.label.password')}}</label>
                            <Password id="password1" v-model="password" :feedback="false" :toggleMask="true" class="mb-4" fluid placeholder="Password"></Password>

                            <div class="flex items-center justify-between mt-2 mb-8 gap-8">
                                <div class="flex items-center">
                                    <Checkbox id="rememberme1" v-model="checked" binary class="mr-2"></Checkbox>
                                    <label for="rememberme1">{{$tt('login.button.rememberMe')}}</label>
                                </div>
                                <span class="font-medium no-underline ml-2 text-right cursor-pointer text-primary">{{$tt('login.button.forgotPass')}}</span>
                            </div>
                            <Button class="w-full" :label="$tt('login.button.signIn')" @click="login"></Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
:deep(.pi-eye) {
    transform: scale(1.6);
    margin-right: 1rem;
}

:deep(.pi-eye-slash) {
    transform: scale(1.6);
    margin-right: 1rem;
}
</style>
