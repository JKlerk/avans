<template>
    <div class="shadow rounded-lg my-4 select-none">
        <div class="flex w-full hover:bg-gray-200 p-4 transition duration-100" @click="visible = !visible">
            <p class="mx-2">{{ artist.id }}.</p>
            <p>{{ artist.name }}</p>
        </div>
        <transition name="fade">
            <div v-if="visible" class="p-4 border-t">
                <p>User ID: {{ artist.id }}</p>
                <p>Name: {{ artist.name }}</p>
                <p>Band_ID: {{ artist.band_id }}</p>
            </div>
        </transition>
    </div>
</template>

<script>
export default {
    props: ['artist'],
    data(){
        return{
            visible: false
        }
    }
}
</script>

<style scoped>
.fade-enter-active, .fade-leave-active {
    transition: all 0.1s ease-in-out;
    -webkit-transition: all .2s ease-in-out; /* WebKit */
    -moz-transition: all .2s ease-in-out; /* Mozilla */
    -o-transition: all .2s ease-in-out; /* Opera */
    -ms-transition: all .2s ease-in-out; /* Internet Explorer */
  max-height: 250px;
  overflow: auto;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
   max-height: 0px;
}
</style>