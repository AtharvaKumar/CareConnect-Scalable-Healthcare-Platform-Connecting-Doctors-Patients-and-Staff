import React, { useEffect, useState } from 'react'
import axios from '../utils/axios'

export default function Appointments(){
  const [list, setList] = useState([])
  const [form, setForm] = useState({ doctorId:'', patientId:'', startTime:'', endTime:'' })

  const load = async () => {
    const { data } = await axios.get('/appointment/api/v1/appointments')
    setList(data)
  }
  useEffect(()=>{ load() }, [])

  const add = async (e) => {
    e.preventDefault()
    await axios.post('/appointment/api/v1/appointments', { 
      ...form,
      doctorId: +form.doctorId,
      patientId: +form.patientId
    })
    setForm({ doctorId:'', patientId:'', startTime:'', endTime:'' })
    load()
  }

  return (
    <div>
      <h2>Appointments</h2>
      <form onSubmit={add} style={{display:'grid', gap:6, maxWidth:420}}>
        <input placeholder="Doctor ID" value={form.doctorId} onChange={e=>setForm({...form, doctorId:e.target.value})}/>
        <input placeholder="Patient ID" value={form.patientId} onChange={e=>setForm({...form, patientId:e.target.value})}/>
        <input placeholder="Start (YYYY-MM-DDTHH:mm)" value={form.startTime} onChange={e=>setForm({...form, startTime:e.target.value})}/>
        <input placeholder="End (YYYY-MM-DDTHH:mm)" value={form.endTime} onChange={e=>setForm({...form, endTime:e.target.value})}/>
        <button>Add</button>
      </form>
      <ul>
        {list.map(a=>(<li key={a.id}>#{a.id} — D{a.doctorId} → P{a.patientId} — {a.startTime} to {a.endTime}</li>))}
      </ul>
    </div>
  )
}
