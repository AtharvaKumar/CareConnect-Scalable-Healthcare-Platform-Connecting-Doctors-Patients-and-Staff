import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      '/doctor': 'http://localhost:8080',
      '/patient': 'http://localhost:8080',
      '/appointment': 'http://localhost:8080',
      '/billing': 'http://localhost:8080'
    }
  }
})
