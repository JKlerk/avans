<template>
    <transition name="fade" v-if="visible">
        <div>
            <div class="bg-black opacity-75 fixed min-h-screen z-0 w-full top-0"></div>
            <div class="flex justify-center">
                <div class="z-40 bg-white w-1/3 fixed p-4 rounded shadow">
                    <div class="border-b pb-3">
                        <h1 class="text-2xl">Edit Artist</h1>
                        <p class="text-gray-800">Edit your artist!</p>
                    </div>
                    <div class="mt-4">
                        <div class="my-5">
                            <label class="mr-2">Artist name:</label>
                            <input v-model="artist.name" class="border rounded focus:outline-none appearance-none px-1">
                        </div>  
                        <div class="my-5">
                            <p class="mr-2 mb-2">Artist description:</p>
                            <textarea v-model="artist.desc" class="border rounded focus:outline-none appearance-none px-1 w-2/3" />
                        </div>  
                        <div class="flex mt-10">
                            <button class="px-2 py-1 text-white bg-green-500 hover:bg-green-600 rounded w-2/12 mr-5 transition duration-100 focus:outline-none">Save</button>
                            <button @click="visible = false" class="px-2 py-1 text-white bg-red-500 hover:bg-red-600 rounded w-2/12 transition duration-100 focus:outline-none">Close</button>
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
            artist:{}
        }
    }
}
</script>