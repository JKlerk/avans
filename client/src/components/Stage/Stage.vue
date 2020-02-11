<template>
    <div class="shadow rounded-lg my-4 select-none">
        <div class="flex w-full hover:bg-gray-200 p-4 transition duration-100" @click="visible = !visible">
            <p class="mx-2">{{ stage.id }}.</p>
            <p>{{ stage.name }}</p>
        </div>
        <transition name="fade">
            <div v-if="visible" class="p-4 border-t">
                <p>User ID: {{ stage.id }}</p>
                <p>Name: {{ stage.name }}</p>
                <p>Description: {{ stage.desc }}</p>
                <button class="appereance-none bg-green-500 hover:bg-green-600 py-1 text-white rounded px-2 text-sm mt-2 transition duration-100 focus:outline-none" @click="editStage(stage)">Edit Stage</button>
				<button class="appereance-none bg-red-500 hover:bg-red-600 py-1 text-white rounded px-2 text-sm mt-2 ml-4 transition duration-100 focus:outline-none" @click="deleteStage(stage)">Delete Stage</button>
            </div>
        </transition>
    </div>
</template>

<script>
export default {
    props: ['stage'],
    data(){
        return{
            visible: false
        }
    },
    methods:{
        editStage(stage){
            this.$parent.$refs.editstage.stage = stage
            this.$parent.$refs.editstage.visible = true
        },
        deleteStage(stage){
            this.$parent.$refs.deletestage.stage = stage
            this.$parent.$refs.deletestage.visible = true
        }
    }
}
</script>