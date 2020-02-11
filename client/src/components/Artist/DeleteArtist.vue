<template>
    <transition name="fade" v-if="visible">
        <div>
            <div class="bg-black opacity-75 fixed min-h-screen z-0 w-full top-0"></div>
            <div class="flex justify-center">
                <div class="z-40 bg-white w-1/3 fixed p-4 rounded shadow">
                    <div class="border-b pb-3">
                        <h1 class="text-2xl">Delete Artist</h1>
                        <p class="text-gray-800">Removing an artist!</p>
                    </div>
                    <div class="mt-4">
                        <p>You are about to delete artist: {{ artist.name }}</p>
                        <p>Are you sure you want to delete this artist?</p>
                        <p class="text-sm text-gray-500 mt-2">NOTE: This artist will also be removed from his/her band</p>
                        <div class="flex mt-10">
                            <button class="px-2 py-1 text-white bg-green-500 hover:bg-green-600 rounded w-2/12 mr-5 transition duration-100 focus:outline-none">Continue</button>
                            <button @click="visible = false" class="px-2 py-1 text-white bg-red-500 hover:bg-red-600 rounded w-2/12 transition duration-100 focus:outline-none">Cancel</button>
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
            artist: {}
        }
    }
}
</script>