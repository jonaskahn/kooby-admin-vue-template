<script setup>
import { onBeforeMount, ref } from 'vue';
import PatientRequestService from '@/service/PatientRequestService';

const patientRequestService = PatientRequestService.INSTANCE

const keyword = ref('')
const pageNumber = ref(0)
const states = ref([])
const patientRequests = ref()

async function fetchPatientRequest(){
    let params ={
        keyword: keyword.value,
        states: states.value,
        pageNo: pageNumber.value
    }
    const res = await patientRequestService.getPatientRequests(params)
    patientRequests.value = res.payload.data
    console.debug(res.payload)
}

onBeforeMount(async ()=>{
    await fetchPatientRequest()
})
</script>

<template>
    <div class="card">
        <Toolbar class="mb-2">
            <template v-slot:start>
                <Button type="button" label="Add new" icon="pi pi-user-plus" :loading="loading" @click="load" />
            </template>
            <template v-slot:end>
                <Button type="button" label="Search" icon="pi pi-search" :loading="loading" severity="success" @click="load" />
            </template>
        </Toolbar>
    </div>
    <div class="card">
        <div class="font-semibold text-xl mb-4">Danh sách bệnh nhân đến sao</div>
        <DataTable :value="customers2" scrollHeight="400px" scrollable>
            <Column class="font-bold" field="name" frozen header="Name" style="min-width: 200px"></Column>
            <Column field="id" header="Id" style="min-width: 100px"></Column>
            <Column field="name" header="Name" style="min-width: 200px"></Column>
            <Column field="country.name" header="Country" style="min-width: 200px"></Column>
            <Column field="date" header="Date" style="min-width: 200px"></Column>
            <Column field="company" header="Company" style="min-width: 200px"></Column>
            <Column field="status" header="Status" style="min-width: 200px"></Column>
            <Column field="activity" header="Activity" style="min-width: 200px"></Column>
            <Column field="representative.name" header="Representative" style="min-width: 200px"></Column>
            <Column :frozen="balanceFrozen" alignFrozen="right" field="balance" header="Balance" style="min-width: 200px">
                <template #body="{ data }">
                    <span class="font-bold">{{ formatCurrency(data.balance) }}</span>
                </template>
            </Column>
        </DataTable>
    </div>
</template>

<style lang="scss" scoped>
:deep(.p-datatable-frozen-tbody) {
    font-weight: bold;
}

:deep(.p-datatable-scrollable .p-frozen-column) {
    font-weight: bold;
}
</style>
