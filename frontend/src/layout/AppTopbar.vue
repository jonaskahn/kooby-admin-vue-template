<script setup>
import { useLayout } from '@/layout/composables/layout';
import AppConfigurator from './AppConfigurator.vue';
import LangPalette from '@/components/LangPalette.vue';

const { onMenuToggle, toggleDarkMode, isDarkTheme } = useLayout();
</script>

<template>
    <div class="layout-topbar">
        <div class="layout-topbar-logo-container">
            <button class="layout-menu-button layout-topbar-action" @click="onMenuToggle">
                <i class="pi pi-bars"></i>
            </button>
            <router-link class="layout-topbar-logo" to="/">
                <img src="/demo/images/logo-white.png" style="width: auto; height: 40px;"/>
            </router-link>
        </div>

        <div class="layout-topbar-actions">
            <div class="layout-config-menu">
                <button class="layout-topbar-action" type="button" @click="toggleDarkMode">
                    <i :class="['pi', { 'pi-moon': isDarkTheme, 'pi-sun': !isDarkTheme }]"></i>
                </button>

                <div class="relative">
                    <button
                        v-styleclass="{ selector: '@next', enterFromClass: 'hidden', enterActiveClass: 'animate-scalein', leaveToClass: 'hidden', leaveActiveClass: 'animate-fadeout', hideOnOutsideClick: true }"
                        class="layout-topbar-action layout-topbar-action-highlight"
                        type="button"
                    >
                        <i class="pi pi-palette"></i>
                    </button>
                    <AppConfigurator :show-menu-mode="true" />
                </div>
            </div>

            <lang-palette />

            <button
                v-styleclass="{ selector: '@next', enterFromClass: 'hidden', enterActiveClass: 'animate-scalein', leaveToClass: 'hidden', leaveActiveClass: 'animate-fadeout', hideOnOutsideClick: true }"
                class="layout-topbar-menu-button layout-topbar-action"
            >
                <i class="pi pi-ellipsis-v"></i>
            </button>

            <div class="layout-topbar-menu hidden lg:block">
                <div class="layout-topbar-menu-content">
                    <button class="layout-topbar-action" type="button">
                        <i class="pi pi-calendar"></i>
                        <span>Calendar</span>
                    </button>
                    <button class="layout-topbar-action" type="button">
                        <i class="pi pi-inbox"></i>
                        <span>Messages</span>
                    </button>
                    <button class="layout-topbar-action" type="button">
                        <i class="pi pi-user"></i>
                        <span>Profile</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>
