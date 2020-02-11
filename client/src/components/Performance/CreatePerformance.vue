<template>
    <transition name="fade" v-if="visible">
        <div>
            <div class="bg-black opacity-75 fixed min-h-screen z-0 w-full top-0"></div>
            <div class="flex justify-center">
                <div class="z-40 bg-white w-1/3 fixed p-4 rounded shadow">
                    <div class="border-b pb-3">
                        <h1 class="text-2xl">Adding a performance</h1>
                        <p class="text-gray-800">Adding a new performance!</p>
                    </div>
                    <div class="mt-4">
                        <div class="my-5">
                            <label class="mr-2">Performance name:</label>
                            <input v-model="performance.name" class="border rounded focus:outline-none appearance-none px-1">
                        </div>   
                        <div class="my-5">
                            <label class="mr-2">Performance time:</label>
                            <input class="border px-1 rounded focus:outline-none" type="datetime-local" v-model="performance.time" value="2020-06-12T19:30" min="2020-01-07T00:00" max="2025-1-14T00:00">

                        </div>  
                        <div class="flex mt-10">
                            <button @click="getTime" class="px-2 py-1 text-white bg-green-500 hover:bg-green-600 rounded w-2/12 mr-5 transition duration-100 focus:outline-none">Save</button>
                            <button @click="visible = false, performance = {}" class="px-2 py-1 text-white bg-red-500 hover:bg-red-600 rounded w-2/12 transition duration-100 focus:outline-none">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>
<script>
export default {
    data(){
        return{
            visible: false,
            performance:{}
        }
    },
    methods:{
        getTime(){
            console.log(this.performance.time)
        }
    }
}
</script>