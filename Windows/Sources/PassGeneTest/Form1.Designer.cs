namespace WindowsFormsApplication1
{
    partial class Form1
    {
        /// <summary>
        /// Требуется переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Обязательный метод для поддержки конструктора - не изменяйте
        /// содержимое данного метода при помощи редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.seVersion = new System.Windows.Forms.NumericUpDown();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.teMastrPassword = new System.Windows.Forms.TextBox();
            this.teSite = new System.Windows.Forms.TextBox();
            this.teLogin = new System.Windows.Forms.TextBox();
            this.sePasswordLength = new System.Windows.Forms.NumericUpDown();
            this.tePassword = new System.Windows.Forms.TextBox();
            this.btnGenerate = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.cbSymbolsSet = new System.Windows.Forms.ComboBox();
            this.button1 = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.seVersion)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sePasswordLength)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(84, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Мастер-пароль";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(13, 43);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(31, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Сайт";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 76);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(38, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Логин";
            // 
            // seVersion
            // 
            this.seVersion.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.seVersion.Location = new System.Drawing.Point(104, 110);
            this.seVersion.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.seVersion.Name = "seVersion";
            this.seVersion.Size = new System.Drawing.Size(287, 20);
            this.seVersion.TabIndex = 3;
            this.seVersion.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(13, 112);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(44, 13);
            this.label4.TabIndex = 4;
            this.label4.Text = "Версия";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(13, 147);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(40, 13);
            this.label5.TabIndex = 5;
            this.label5.Text = "Длина";
            // 
            // teMastrPassword
            // 
            this.teMastrPassword.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.teMastrPassword.Location = new System.Drawing.Point(104, 5);
            this.teMastrPassword.Name = "teMastrPassword";
            this.teMastrPassword.PasswordChar = '*';
            this.teMastrPassword.Size = new System.Drawing.Size(250, 20);
            this.teMastrPassword.TabIndex = 6;
            // 
            // teSite
            // 
            this.teSite.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.teSite.Location = new System.Drawing.Point(104, 35);
            this.teSite.Name = "teSite";
            this.teSite.Size = new System.Drawing.Size(287, 20);
            this.teSite.TabIndex = 7;
            // 
            // teLogin
            // 
            this.teLogin.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.teLogin.Location = new System.Drawing.Point(104, 69);
            this.teLogin.Name = "teLogin";
            this.teLogin.Size = new System.Drawing.Size(287, 20);
            this.teLogin.TabIndex = 8;
            // 
            // sePasswordLength
            // 
            this.sePasswordLength.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.sePasswordLength.Location = new System.Drawing.Point(104, 140);
            this.sePasswordLength.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.sePasswordLength.Name = "sePasswordLength";
            this.sePasswordLength.Size = new System.Drawing.Size(287, 20);
            this.sePasswordLength.TabIndex = 9;
            this.sePasswordLength.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // tePassword
            // 
            this.tePassword.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tePassword.Location = new System.Drawing.Point(16, 211);
            this.tePassword.Multiline = true;
            this.tePassword.Name = "tePassword";
            this.tePassword.Size = new System.Drawing.Size(375, 117);
            this.tePassword.TabIndex = 10;
            // 
            // btnGenerate
            // 
            this.btnGenerate.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.btnGenerate.Location = new System.Drawing.Point(16, 335);
            this.btnGenerate.Name = "btnGenerate";
            this.btnGenerate.Size = new System.Drawing.Size(374, 23);
            this.btnGenerate.TabIndex = 11;
            this.btnGenerate.Text = "Сформировать пароль";
            this.btnGenerate.UseVisualStyleBackColor = true;
            this.btnGenerate.Click += new System.EventHandler(this.btnGenerate_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(16, 183);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(92, 13);
            this.label6.TabIndex = 12;
            this.label6.Text = "Набор символов";
            // 
            // cbSymbolsSet
            // 
            this.cbSymbolsSet.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.cbSymbolsSet.FormattingEnabled = true;
            this.cbSymbolsSet.Items.AddRange(new object[] {
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!.,?\'\":;@#$%|^&()[" +
                "]{}<>*_+-=/\\",
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",
            "abcdefghijklmnopqrstuvwxyz1234567890"});
            this.cbSymbolsSet.Location = new System.Drawing.Point(104, 175);
            this.cbSymbolsSet.Name = "cbSymbolsSet";
            this.cbSymbolsSet.Size = new System.Drawing.Size(287, 21);
            this.cbSymbolsSet.TabIndex = 13;
            this.cbSymbolsSet.Text = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!.,?\'\":;@#$%|^&()[" +
                "]{}<>*_+-=/\\";
            // 
            // button1
            // 
            this.button1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.button1.Location = new System.Drawing.Point(360, 3);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(31, 23);
            this.button1.TabIndex = 14;
            this.button1.UseVisualStyleBackColor = true;
            this.button1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.button1_MouseDown);
            this.button1.MouseUp += new System.Windows.Forms.MouseEventHandler(this.button1_MouseUp);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(403, 366);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.cbSymbolsSet);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.btnGenerate);
            this.Controls.Add(this.tePassword);
            this.Controls.Add(this.sePasswordLength);
            this.Controls.Add(this.teLogin);
            this.Controls.Add(this.teSite);
            this.Controls.Add(this.teMastrPassword);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.seVersion);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.seVersion)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sePasswordLength)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.NumericUpDown seVersion;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox teMastrPassword;
        private System.Windows.Forms.TextBox teSite;
        private System.Windows.Forms.TextBox teLogin;
        private System.Windows.Forms.NumericUpDown sePasswordLength;
        private System.Windows.Forms.TextBox tePassword;
        private System.Windows.Forms.Button btnGenerate;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ComboBox cbSymbolsSet;
        private System.Windows.Forms.Button button1;
    }
}

