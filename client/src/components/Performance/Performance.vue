<template>
    <div class="shadow rounded-lg my-4 select-none">
        <div class="flex w-full hover:bg-gray-200 p-4 transition duration-100" @click="visible = !visible">
            <p class="mx-2">{{ performance.id }}.</p>
            <p>{{ performance.name }}</p>
        </div>
        <transition name="fade">
            <div v-if="visible" class="p-4 border-t">
                <p>User ID: {{ performance.id }}</p>
                <p>Name: {{ performance.name }}</p>
                <p>Time: {{ performance.time }}</p>
                <button class="appereance-none bg-green-500 hover:bg-green-600 py-1 text-white rounded px-2 text-sm mt-2 transition duration-100 focus:outline-none" @click="editperformance(performance)">Edit performance</button>
				<button class="appereance-none bg-red-500 hover:bg-red-600 py-1 text-white rounded px-2 text-sm mt-2 ml-4 transition duration-100 focus:outline-none" @click="deleteperformance(performance)">Delete performance</button>
            </div>
        </transition>
    </div>
</template>

<script>
export default {
    props: ['performance'],
    data(){
        return{
            visible: false
        }
    },
    methods:{
        editperformance(performance){
            this.$parent.$refs.editperformance.performance = performance
            this.$parent.$refs.editperformance.visible = true
        },
        deleteperformance(performance){
            this.$parent.$refs.deleteperformance.performance = performance
            this.$parent.$refs.deleteperformance.visible = true
        }
    }
}
</script>