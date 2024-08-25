<script setup>
import { ref } from 'vue';

import AppMenuItem from './AppMenuItem.vue';

const model = ref([
    {
        label: 'page.menu-bar.home',
        items: [{ label: 'page.menu-bar.home.dashboard', icon: 'pi pi-fw pi-chart-scatter', to: '/' }]
    },
    {
        label: 'page.menu-bar.patient-reception',
        items: [
            { label: 'page.menu-bar.patient-reception.reception', icon: 'pi pi-fw pi-pen-to-square', to: '/patient/reception' },
            { label: 'page.menu-bar.patient-reception.list-report', icon: 'pi pi-fw pi-clipboard', to: '/patient/reception' },
            { label: 'page.menu-bar.patient-reception.delivery-report', icon: 'pi pi-fw pi-list', to: '/patient/reception' },
            { label: 'page.menu-bar.patient-reception.mailing-report', icon: 'pi pi-fw pi-list-check', to: '/patient/reception', class: 'rotated-icon' },
        ]
    },
    {
        label: 'page.menu-bar.medical-record',
        icon: 'pi pi-fw pi-briefcase',
        items: [
            { label: 'page.menu-bar.medical-record.copy-list', icon: 'pi pi-fw pi-receipt', to: '/patient/reception' },
            { label: 'page.menu-bar.medical-record.copy-medical-record', icon: 'pi pi-fw pi-print', to: '/patient/reception' },
            { label: 'page.menu-bar.medical-record.borrow-medical-record', icon: 'pi pi-fw pi-bookmark', to: '/patient/reception' },
            { label: 'page.menu-bar.medical-record.refund-medical-record', icon: 'pi pi-fw pi-bookmark-fill', to: '/patient/reception' },
        ]
    },
    {
        label: 'page.menu-bar.number-allocation',
        items: [
            { label: 'page.menu-bar.number-allocation.transfer', icon: 'pi pi-fw pi-id-card', to: '/patient/reception' },
            { label: 'page.menu-bar.number-allocation.death-certificate', icon: 'pi pi-fw pi-id-card', to: '/patient/reception' },
        ]
    },
    {
        label: 'page.menu-bar.medical-record-storage',
        items: [
            { label: 'page.menu-bar.medical-record-storage.storage-number', icon: 'pi pi-fw pi-server', to: '/patient/reception' },
        ]
    }
]);
</script>

<template>
    <ul class="layout-menu">
        <template v-for="(item, i) in model" :key="item">
            <app-menu-item v-if="!item.separator" :index="i" :item="item"></app-menu-item>
            <li v-if="item.separator" class="menu-separator"></li>
        </template>
    </ul>
</template>

<style lang="scss" scoped></style>
