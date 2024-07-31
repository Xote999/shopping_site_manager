<template>
    <div class="login_container">
       <div class="login_form">
            <h3 class="title">ログイン</h3>
            <el-divider></el-divider>
            <el-form :model="loginForm" ref="formRef" label-width="auto">
                <!-- メールアドレス -->
                <el-form-item>
                    <el-input v-model="loginForm.account" placeholder="メールアドレス">
                        <template #prefix>
                            <el-icon class="el-input_icon">
                                <i-ep-message/>
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <!-- パスワード -->
                <el-form-item>
                    <el-input v-model="loginForm.password" placeholder="パスワード" type="password">
                        <template #prefix>
                            <el-icon class="el-input_icon">
                                <i-ep-lock/>
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <div class="supplement">
                    <el-checkbox v-model="loginForm.remenberMe" label="ログイン状態を保持する" size="large"></el-checkbox>
                    <el-text class="forget mx-1" type="primary">パスワードを忘れた方はこちら</el-text>
                </div>
                <!-- ボタン -->
                <el-form-item>
                    <el-button style="width:100%;" type="primary">ログイン</el-button>
                </el-form-item>
                <el-divider>または</el-divider>
                <!-- ソーシャルログイン -->
                <div class="social_login">
                    <el-icon class="gap"><i-logos-google-icon/></el-icon>
                    <el-icon class="gap"><i-logos-facebook/></el-icon>
                    <el-icon class="gap"><i-pajamas-twitter/></el-icon>
                    <el-icon class="gap"><i-logos-apple/></el-icon>
                </div>
            </el-form>
       </div>
    </div>
</template>

<script setup>
    //refのインポート
    import {ref} from "vue"

    const loginForm = ref({
        account:undefined,
        password:undefined
    })
</script>

<style lang="scss" scoped>

.login_container{
    background-image: url('../assets/bgimg/1.jpg');
    background-size: 100%;
    height: 100vh;
    display: flex;
    justify-content: flex-end;

    .login_form{
        display:flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        width: 50%;
        background-color: white;

        .supplement{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;

            .forget{
                cursor: pointer;
            }
        }

        .social_login{
            display: flex;
            justify-content: space-around;

            .gap{
                font-size: 2em;
                cursor: pointer;
            }
        }
    }
}

.el-form{
    width: 60%;
}

.el-form-item{
    width: 100%;
}
</style>